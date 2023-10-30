package odofin.oyejide.twitterlikeapp.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import odofin.oyejide.twitterlikeapp.model.dto.request.Subscribe;
import odofin.oyejide.twitterlikeapp.model.dto.response.ApiResponse;
import odofin.oyejide.twitterlikeapp.model.entity.SubscriberProducer;
import odofin.oyejide.twitterlikeapp.model.entity.User;
import odofin.oyejide.twitterlikeapp.repository.SubscriberProducerRepository;
import odofin.oyejide.twitterlikeapp.service.SubscriptionService;
import odofin.oyejide.twitterlikeapp.service.UserManagementService;
import odofin.oyejide.twitterlikeapp.utils.MessageUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriberProducerRepository subscriberProducerRepository;
    private final UserManagementService userManagementService;
    @Override
    /**
     * Handles subscription request, performs validations, and processes the subscription logic.
     *
     * @param request The Subscribe request containing subscriber and producer IDs.
     * @return A Mono of ResponseEntity containing a subscription status message.
     */
    public Mono<ResponseEntity<ApiResponse<String>>> subscribe(Subscribe request) {
        Integer subscriberId = request.getSubscriberID();
        Integer producerId = request.getUserId();

        // Check if both subscriber and producer exist
        return Mono.zip(
                        userManagementService.findById(subscriberId),
                        userManagementService.findById(producerId)
                )
                .flatMap(tuple -> {
                    User subscriber = tuple.getT1();
                    User producer = tuple.getT2();

                    if (subscriber == null || producer == null) {
                        // Return a response indicating that the user was not found.
                        return Mono.just(ResponseEntity.ok(new ApiResponse<>(MessageUtil.FAIL, HttpStatus.NOT_FOUND.value(), MessageUtil.RECORD_NOT_FOUND)));
                    }

                    // Check if the given user ID is a producer
                    if (!"Producer".equals(producer.getURole())) {
                        return Mono.just(ResponseEntity.ok(new ApiResponse<>(MessageUtil.FAIL, HttpStatus.FORBIDDEN.value(), MessageUtil.SUBSCRIPTION_NOT_ALLOW)));
                    }

                    // Validate that a subscriber cannot make multiple subscriptions for the same producer
                    return subscriberProducerRepository.checkSubscriptionStatus(subscriberId, producerId)
                            .collectList()
                            .flatMap(existingSubscriptions -> {
                                if (!existingSubscriptions.isEmpty()) {
                                    return Mono.just(ResponseEntity.ok(new ApiResponse<>(MessageUtil.FAIL, HttpStatus.CONFLICT.value(), MessageUtil.DUPLICATE_SUBSCRIPTION)));
                                }

                                // Perform the subscription logic and save the subscription
                                return subscriberProducerRepository.save(SubscriberProducer.builder()
                                                .producerId(producerId)
                                                .subscriberId(subscriberId)
                                                .build())
                                        .thenReturn(ResponseEntity.ok(new ApiResponse<>(MessageUtil.SUCCESS, HttpStatus.OK.value(), MessageUtil.SUBSCRIPTION_SUCCESSFUL)));
                            });
                })
                .switchIfEmpty(Mono.defer(() ->
                        Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(new ApiResponse<>(MessageUtil.FAIL, HttpStatus.INTERNAL_SERVER_ERROR.value(), MessageUtil.INTERNAL_ERROR)))
                ));
    }


}
