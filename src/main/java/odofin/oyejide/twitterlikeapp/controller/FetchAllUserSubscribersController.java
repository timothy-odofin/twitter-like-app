package odofin.oyejide.twitterlikeapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import odofin.oyejide.twitterlikeapp.model.dto.response.ApiResponse;
import odofin.oyejide.twitterlikeapp.model.dto.response.MessageResponse;
import odofin.oyejide.twitterlikeapp.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@Tag(description = "Fetch all User message by Subscriber", name = "SUBSCRIBER")
@RequiredArgsConstructor
public class FetchAllUserSubscribersController {
private final MessageService messageService;
    @GetMapping("/getAllUserMessages/{subscriberId}")
    @Operation(description = "Get all Messages by Subscriber ID",parameters = {
            @Parameter(name = "subscriberId", in = ParameterIn.PATH, required = true, description = "the id of the subscriber")
    })
    public Mono<ResponseEntity<ApiResponse<List<MessageResponse>>>> getAllUserMessages(@PathVariable Integer subscriberId) {
        return  messageService.getMessageBySubscriberId(subscriberId);
    }
}
