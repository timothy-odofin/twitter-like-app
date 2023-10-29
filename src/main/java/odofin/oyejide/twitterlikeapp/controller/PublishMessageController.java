package odofin.oyejide.twitterlikeapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import odofin.oyejide.twitterlikeapp.model.dto.request.PublishMessageRequest;
import odofin.oyejide.twitterlikeapp.model.dto.response.ApiResponse;
import odofin.oyejide.twitterlikeapp.model.dto.response.PublishMessageResponse;
import odofin.oyejide.twitterlikeapp.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/publish")
@Tag(description = "PublishMessage", name = "PUBLISING")
@RequiredArgsConstructor
public class PublishMessageController {
private final MessageService messageService;
    @PostMapping("/message")
    @Operation(description = "Publishes User message")
    public Mono<ResponseEntity<ApiResponse<String>>> publishMessage(@Valid @RequestBody PublishMessageRequest request) {
        return messageService.publishMessage(request);
    }
}
