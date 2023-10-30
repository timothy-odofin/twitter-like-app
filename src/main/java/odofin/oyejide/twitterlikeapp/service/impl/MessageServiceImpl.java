package odofin.oyejide.twitterlikeapp.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import odofin.oyejide.twitterlikeapp.model.dto.request.PublishMessageRequest;
import odofin.oyejide.twitterlikeapp.model.dto.response.ApiResponse;
import odofin.oyejide.twitterlikeapp.model.dto.response.MessageResponse;
import odofin.oyejide.twitterlikeapp.model.dto.response.UserResponse;
import odofin.oyejide.twitterlikeapp.model.dto.response.exception.RecordNotFounException;
import odofin.oyejide.twitterlikeapp.model.entity.Message;
import odofin.oyejide.twitterlikeapp.model.entity.User;
import odofin.oyejide.twitterlikeapp.repository.MessageRepository;
import odofin.oyejide.twitterlikeapp.repository.SubscriberProducerRepository;
import odofin.oyejide.twitterlikeapp.repository.UserRepository;
import odofin.oyejide.twitterlikeapp.service.MessageService;
import odofin.oyejide.twitterlikeapp.utils.MessageUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final SubscriberProducerRepository subscriberProducerRepository;
    private Mono<User> validateUserExists(Integer userId) {
        return userRepository.findById(userId)
                .switchIfEmpty(Mono.error(new RecordNotFounException("User with ID " + userId + " not found")));
    }
    /**
     * Processes a Flux of messages to create a list of MessageResponses.
     * Each message is mapped to a UserResponse and then used to build a MessageResponse.
     * The list of MessageResponses is wrapped in a ResponseEntity with appropriate status and message.
     *
     * @param messageFlux A Flux of Message objects.
     * @return A Mono of ResponseEntity containing a list of MessageResponses.
     */
    private Mono<ResponseEntity<ApiResponse<List<MessageResponse>>>> producerOutcome(Flux<Message> messageFlux){
        return messageFlux.flatMap(message -> {
                    Mono<UserResponse> userResponseMono = userRepository.findById(message.getUid())
                            .map(user -> UserResponse.builder()
                                    .uid(user.getUid())
                                    .uName(user.getUName())
                                    .build());
                    return userResponseMono.map(userResponse -> MessageResponse.builder()
                            .contents(message.getContents())
                            .mid(message.getMid())
                            .postedBy(userResponse)
                            .build());
                })
                .collectList()
                .map(userResponses -> ResponseEntity.ok(new ApiResponse<>(MessageUtil.SUCCESS, HttpStatus.OK.value(), userResponses)));
    }

    /**
     * Retrieves messages associated with a specific user ID after validating its existence.
     * This method combines the validation and message retrieval steps.
     *
     * @param userId The ID of the user to retrieve messages for.
     * @return A Mono of ResponseEntity containing a list of MessageResponses.
     */
    @Override
    public Mono<ResponseEntity<ApiResponse<List<MessageResponse>>>> getUserMessages(Integer userId) {
        return validateUserExists(userId)
                .then(producerOutcome(messageRepository.listMessageByProducerId(userId)));
    }

    /**
     * Retrieves messages associated with a subscriber after validating the user's existence.
     * This method combines the validation and message retrieval steps.
     *
     * @param userId The ID of the subscriber to retrieve messages for.
     * @return A Mono of ResponseEntity containing a list of MessageResponses.
     */
    @Override
    public Mono<ResponseEntity<ApiResponse<List<MessageResponse>>>> getMessageBySubscriberId(Integer userId) {
        return validateUserExists(userId).then(subscriberProducerRepository.listProducerBySubscriber(userId)
                .collectList()
                .flatMap(producerIds -> producerOutcome(messageRepository.listMessageByProducerIdIn(producerIds))));
    }

    /**
     * Publishes a message for a specific user after validating the user's existence.
     *
     * @param request The PublishMessageRequest containing user ID and message content.
     * @return A Mono of ResponseEntity containing a success message.
     */
    @Override
    public Mono<ResponseEntity<ApiResponse<String>>> publishMessage(PublishMessageRequest request) {
        Integer userId = request.getUserId();
        String contents = request.getMessage();
        return validateUserExists(userId)
                .flatMap(user -> messageRepository.save(Message.builder()
                        .contents(contents)
                        .uid(userId)
                        .build()))
                .thenReturn(ResponseEntity.ok(new ApiResponse<>(MessageUtil.SUCCESS, HttpStatus.OK.value(), MessageUtil.MESSAGE_PUBLISHED_SUCCESSFULLY)));
    }

}
