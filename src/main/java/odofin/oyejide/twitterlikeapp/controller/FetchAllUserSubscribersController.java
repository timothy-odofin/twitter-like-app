package odofin.oyejide.twitterlikeapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import odofin.oyejide.twitterlikeapp.model.dto.response.GetMessagesResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@Tag(description = "Fetch all User Subscribers", name = "SUBSCRIBER")
public class FetchAllUserSubscribersController {

    @GetMapping("/getAllUserMessages/{subscriberId}")
    @Operation(description = "Get all Subscribers by Subscriber ID",parameters = {
            @Parameter(name = "subscriberId", in = ParameterIn.PATH, required = true, description = "the id of the subscriber")
    })
    public Mono<GetMessagesResponse> getAllUserMessages(@PathVariable Integer subscriberId) {
        return Mono.just(new GetMessagesResponse());
    }
}
