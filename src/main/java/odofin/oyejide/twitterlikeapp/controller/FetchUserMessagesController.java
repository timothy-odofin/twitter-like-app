package odofin.oyejide.twitterlikeapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import odofin.oyejide.twitterlikeapp.model.dto.response.GetMessagesResponse;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@Tag(description = "Fetch User Messages", name = "USER")
public class FetchUserMessagesController {

    @GetMapping("/getUserMessages/{userId}")
    @Operation(description = "Get all messages by User ID", parameters = {
            @Parameter(name = "userId", in = ParameterIn.PATH, required = true, description = "the id of the User")
    })
    public Mono<GetMessagesResponse> getUserMessages(@PathVariable Long userId) {
        return Mono.just(new GetMessagesResponse());
    }
}
