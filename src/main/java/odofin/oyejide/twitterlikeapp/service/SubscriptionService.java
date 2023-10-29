package odofin.oyejide.twitterlikeapp.service;

import odofin.oyejide.twitterlikeapp.model.dto.request.Subscribe;
import odofin.oyejide.twitterlikeapp.model.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

public interface SubscriptionService {
    Mono<ResponseEntity<ApiResponse<String>>> subscribe(@RequestBody Subscribe request);
}
