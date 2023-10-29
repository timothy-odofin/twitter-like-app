package odofin.oyejide.twitterlikeapp.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import odofin.oyejide.twitterlikeapp.model.dto.response.ApiResponse;
import odofin.oyejide.twitterlikeapp.model.dto.response.MessageResponse;
import odofin.oyejide.twitterlikeapp.model.dto.response.UserResponse;
import odofin.oyejide.twitterlikeapp.model.entity.Message;
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
    @Override
    public Mono<ResponseEntity<ApiResponse<List<MessageResponse>>>> getUserMessages(Integer userId) {
        return producerOutcome(messageRepository.listMessageByProducerId(userId));
    }
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

    @Override
    public Mono<ResponseEntity<ApiResponse<List<MessageResponse>>>> getMessageBySubscriberId(Integer userId) {
        return subscriberProducerRepository.listProducerBySubscriber(userId)
                .collectList()
                .flatMap(producerIds -> producerOutcome(messageRepository.listMessageByProducerIdIn(producerIds)));
    }
}
