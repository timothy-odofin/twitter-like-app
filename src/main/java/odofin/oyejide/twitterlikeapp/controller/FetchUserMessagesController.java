package odofin.oyejide.twitterlikeapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import odofin.oyejide.twitterlikeapp.model.dto.response.ApiResponse;
import odofin.oyejide.twitterlikeapp.model.dto.response.GetMessagesResponse;
import odofin.oyejide.twitterlikeapp.model.dto.response.MessageResponse;
import odofin.oyejide.twitterlikeapp.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@Tag(description = "Fetch User Messages", name = "MESSAGE")
@RequiredArgsConstructor
public class FetchUserMessagesController {
private final MessageService messageService;
    @GetMapping("/getUserMessages/{userId}")
    @Operation(description = "Get all messages by User ID", parameters = {
            @Parameter(name = "userId", in = ParameterIn.PATH, required = true, description = "the id of the User")
    })
    public Mono<ResponseEntity<ApiResponse<List<MessageResponse>>>> getUserMessages(@PathVariable Integer userId, @RequestHeader("api_key")String api_key) {
        return messageService.getUserMessages(userId);
    }
}
