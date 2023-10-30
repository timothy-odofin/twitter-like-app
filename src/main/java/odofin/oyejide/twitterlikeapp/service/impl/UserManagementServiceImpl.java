package odofin.oyejide.twitterlikeapp.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import odofin.oyejide.twitterlikeapp.model.dto.TokenDetails;
import odofin.oyejide.twitterlikeapp.model.dto.request.LoginRequest;
import odofin.oyejide.twitterlikeapp.model.dto.response.ApiResponse;
import odofin.oyejide.twitterlikeapp.model.dto.response.LoginResponse;
import odofin.oyejide.twitterlikeapp.model.dto.response.UserResponse;
import odofin.oyejide.twitterlikeapp.model.dto.response.exception.RecordNotFounException;
import odofin.oyejide.twitterlikeapp.model.entity.User;
import odofin.oyejide.twitterlikeapp.repository.UserRepository;
import odofin.oyejide.twitterlikeapp.service.UserManagementService;
import odofin.oyejide.twitterlikeapp.utils.AppUtils;
import odofin.oyejide.twitterlikeapp.utils.MessageUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserManagementServiceImpl implements UserManagementService {
    private final UserRepository userRepository;
    /**
     * Validates user credentials and generates a token upon successful authentication.
     * Returns a ResponseEntity containing the generated token in a map if authentication is successful.
     * Otherwise, throws a RecordNotFoundException with an appropriate error message.
     *
     * @param request The LoginRequest containing username and user ID.
     * @return A Mono of ResponseEntity containing a map with the generated token.
     */
    @Override
    public Mono<ResponseEntity<ApiResponse<Map<String,String>>>> login(LoginRequest request) {
        return userRepository.findByUNameAndUid(request.getUsername(), request.getId())
                .flatMap(user -> {
                    String token = AppUtils.generateToken(user.getUid(), user.getURole());
                    Map<String, String> tokenMap = new HashMap<>();
                    tokenMap.put("token", token);
                    return Mono.just(ResponseEntity.ok(new ApiResponse<>(MessageUtil.SUCCESS, HttpStatus.OK.value(), tokenMap)));
                })
                .switchIfEmpty(Mono.error(new RecordNotFounException(MessageUtil.USER_NOT_FOUND)));
    }

    /**
     * Retrieves a user by their ID and returns it as a Mono<User>.
     * Throws a RecordNotFoundException if no user is found with the given ID.
     *
     * @param id The ID of the user to retrieve.
     * @return A Mono of User.
     */
    @Override
    public Mono<User> findById(Integer id) {
        return userRepository.findById(id).switchIfEmpty(Mono.error(new RecordNotFounException(MessageUtil.RECORD_NOT_FOUND)));
    }

    /**
     * Decodes a token to validate its authenticity and retrieve token details.
     *
     * @param token The token to decode.
     * @param currentEndpoint The current endpoint where the token is being validated.
     * @return A Mono of TokenDetails containing the decoded token information.
     */
    @Override
    public Mono<TokenDetails> isTokenValid(String token, String currentEndpoint) {
        return Mono.just(AppUtils.decodeToken(token, currentEndpoint));
    }

    /**
     * Retrieves a list of users based on their role and maps them to UserResponse objects.
     * Returns a ResponseEntity containing the list of UserResponse objects.
     *
     * @param roleName The role name used to filter users.
     * @return A Mono of ResponseEntity containing a list of UserResponse objects.
     */
    @Override
    public Mono<ResponseEntity<ApiResponse<List<UserResponse>>>> listUserByRoles(String roleName) {
        return userRepository.listUserByRoles(roleName)
                .map(user -> UserResponse.builder()
                        .uName(user.getUName())
                        .uid(user.getUid())
                        .build())
                .collectList()
                .map(userResponses -> ResponseEntity.ok(new ApiResponse<>(MessageUtil.SUCCESS,HttpStatus.OK.value(),userResponses)));
    }

}
