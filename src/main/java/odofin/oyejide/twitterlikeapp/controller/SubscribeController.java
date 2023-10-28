package odofin.oyejide.twitterlikeapp.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import odofin.oyejide.twitterlikeapp.model.dto.request.Subscribe;
import odofin.oyejide.twitterlikeapp.model.dto.response.SubscribeResponse;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/subscribe")
@Api(tags = "Subscribe")
public class SubscribeController {

    @PostMapping
    @ApiOperation(value = "Subscribe to an account", notes = "Subscribes the user to an account of their choice")
    public Mono<SubscribeResponse> subscribe(@RequestBody Mono<Subscribe> request) {
        return null;
    }
}
