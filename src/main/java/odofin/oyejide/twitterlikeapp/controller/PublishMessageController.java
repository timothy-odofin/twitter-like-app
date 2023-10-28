package odofin.oyejide.twitterlikeapp.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import odofin.oyejide.twitterlikeapp.model.dto.request.PublishMessageRequest;
import odofin.oyejide.twitterlikeapp.model.dto.response.PublishMessageResponse;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/publish/message")
@Api(tags = "PublishMessage")
public class PublishMessageController {

    @PostMapping
    @ApiOperation(value = "Publishes User message", notes = "Sends the User's message and returns response")
    public Mono<PublishMessageResponse> publishMessage(@RequestBody Mono<PublishMessageRequest> request) {
        return null;
    }
}
