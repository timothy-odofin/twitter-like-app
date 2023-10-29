package odofin.oyejide.twitterlikeapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import odofin.oyejide.twitterlikeapp.model.dto.request.Subscribe;
import odofin.oyejide.twitterlikeapp.model.dto.response.ApiResponse;
import odofin.oyejide.twitterlikeapp.model.dto.response.SubscribeResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/subscribe")
@Tag(description = "Subscribe", name = "Subscription")
public class SubscribeController {

    @PostMapping
    @Operation(description = "Subscribe to an account")
    public Mono<ResponseEntity<ApiResponse<?>>> subscribe(@RequestBody Subscribe request) {
        return Mono.just(ResponseEntity.ok(new ApiResponse<>()));
    }
}
