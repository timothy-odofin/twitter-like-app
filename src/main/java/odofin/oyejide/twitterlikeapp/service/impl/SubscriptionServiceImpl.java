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
    public Mono<ResponseEntity<ApiResponse<String>>> subscribe(Subscribe request) {
        Integer subscriberId = request.getSubscriberID();
        Integer producerId = request.getUserId();

        return Mono.zip(
                        userManagementService.findById(subscriberId),
                        userManagementService.findById(producerId)
                )
                .flatMap(tuple -> {
                    User subscriber = tuple.getT1();
                    User producer = tuple.getT2();

                    if (subscriber == null || producer == null) {
                        return Mono.just(ResponseEntity.ok(new ApiResponse<>(MessageUtil.FAIL, HttpStatus.NOT_FOUND.value(), MessageUtil.RECORD_NOT_FOUND)));
                    }

                    if (!"Producer".equals(producer.getURole())) {
                        return Mono.just(ResponseEntity.ok(new ApiResponse<>(MessageUtil.FAIL, HttpStatus.FORBIDDEN.value(), MessageUtil.SUBSCRIPTION_NOT_ALLOW)));
                    }

                    return subscriberProducerRepository.checkSubscriptionStatus(subscriberId, producerId)
                            .collectList()
                            .flatMap(existingSubscriptions -> {
                                if (!existingSubscriptions.isEmpty()) {
                                    return Mono.just(ResponseEntity.ok(new ApiResponse<>(MessageUtil.FAIL, HttpStatus.CONFLICT.value(), MessageUtil.DUPLICATE_SUBSCRIPTION)));
                                }

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
