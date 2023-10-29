package odofin.oyejide.twitterlikeapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import odofin.oyejide.twitterlikeapp.model.dto.request.PublishMessageRequest;
import odofin.oyejide.twitterlikeapp.model.dto.response.PublishMessageResponse;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/publish")
@Tag(description = "PublishMessage", name = "PUBLISING")
public class PublishMessageController {

    @PostMapping("/message")
    @Operation(description = "Publishes User message")
    public Mono<PublishMessageResponse> publishMessage(@RequestBody PublishMessageRequest request) {
        return Mono.just(new PublishMessageResponse());
    }
}
