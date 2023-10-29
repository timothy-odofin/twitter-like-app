package odofin.oyejide.twitterlikeapp.service;

import odofin.oyejide.twitterlikeapp.model.dto.TokenDetails;
import odofin.oyejide.twitterlikeapp.model.dto.request.LoginRequest;
import odofin.oyejide.twitterlikeapp.model.dto.response.ApiResponse;
import odofin.oyejide.twitterlikeapp.model.dto.response.LoginResponse;
import odofin.oyejide.twitterlikeapp.model.dto.response.UserResponse;
import odofin.oyejide.twitterlikeapp.model.entity.User;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

public interface UserManagementService {
    Mono<ResponseEntity<ApiResponse<Map<String,String>>>> login(LoginRequest request);
    Mono<User> findById(Integer id);
    Mono<TokenDetails> isTokenValid(String token, String currentEndpoint);
    Mono<ResponseEntity<ApiResponse<List<UserResponse>>>> listUserByRoles(String roleName);
}
