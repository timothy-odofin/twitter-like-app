package odofin.oyejide.twitterlikeapp.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import odofin.oyejide.twitterlikeapp.model.dto.response.GetMessagesResponse;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@Api(tags = "Fetch all User Subscribers")
public class FetchAllUserSubscribersController {

    @GetMapping("/getAllUserMessages/{subscriberId}")
    @ApiOperation(value = "Get all Subscribers by Subscriber ID", notes = "Get all Subscribers")
    public Mono<GetMessagesResponse> getAllUserMessages(@PathVariable Integer subscriberId) {
     return null;
    }
}
