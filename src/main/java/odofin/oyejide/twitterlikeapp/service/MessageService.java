package odofin.oyejide.twitterlikeapp.service;

import odofin.oyejide.twitterlikeapp.model.dto.request.PublishMessageRequest;
import odofin.oyejide.twitterlikeapp.model.dto.response.ApiResponse;
import odofin.oyejide.twitterlikeapp.model.dto.response.MessageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

import java.util.List;

public interface MessageService {
    Mono<ResponseEntity<ApiResponse<List<MessageResponse>>>> getUserMessages(Integer userId);
    Mono<ResponseEntity<ApiResponse<List<MessageResponse>>>> getMessageBySubscriberId(Integer subscriberId);
    Mono<ResponseEntity<ApiResponse<String>>> publishMessage(@RequestBody PublishMessageRequest request);
}
