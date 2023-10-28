package odofin.oyejide.twitterlikeapp.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import odofin.oyejide.twitterlikeapp.model.dto.response.GetMessagesResponse;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@Api(tags = "Fetch User Messages")
public class FetchUserMessagesController {

    @GetMapping("/getUserMessages/{userId}")
    @ApiOperation(value = "Get all messages by User ID", notes = "Get all User Messages")
    public Mono<GetMessagesResponse> getUserMessages(@PathVariable Long userId) {
        return null;
    }
}
