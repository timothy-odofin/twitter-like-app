package odofin.oyejide.twitterlikeapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import odofin.oyejide.twitterlikeapp.model.dto.request.Subscribe;
import odofin.oyejide.twitterlikeapp.model.dto.response.ApiResponse;
import odofin.oyejide.twitterlikeapp.model.dto.response.SubscribeResponse;
import odofin.oyejide.twitterlikeapp.service.SubscriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/subscribe")
@Tag(description = "Subscribe", name = "Subscription")
@RequiredArgsConstructor
public class SubscribeController {
private final SubscriptionService subscriptionService;
    @PostMapping
    @Operation(description = "Subscribe to an account")
    public Mono<ResponseEntity<ApiResponse<String>>> subscribe(@Valid @RequestBody Subscribe request) {
      return subscriptionService.subscribe(request);
    }
}
