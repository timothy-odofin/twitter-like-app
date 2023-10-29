package odofin.oyejide.twitterlikeapp.service;

import odofin.oyejide.twitterlikeapp.model.dto.TokenDetails;
import odofin.oyejide.twitterlikeapp.model.dto.request.LoginRequest;
import odofin.oyejide.twitterlikeapp.model.dto.response.LoginResponse;
import odofin.oyejide.twitterlikeapp.model.entity.User;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface UserManagementService {
    Mono<ResponseEntity<LoginResponse>> login(LoginRequest request);
    Mono<User> findById(Integer id);
    Mono<TokenDetails> isTokenValid(String token, String currentEndpoint);
}
