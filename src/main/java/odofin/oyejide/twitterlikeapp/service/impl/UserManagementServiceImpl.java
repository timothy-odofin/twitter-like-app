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

    @Override
    public Mono<ResponseEntity<ApiResponse<Map<String,String>>>> login(LoginRequest request) {
        return userRepository.findByUNameAndUid(request.getUsername(), request.getId())
                .flatMap(user -> {
                    String token = AppUtils.generateToken(user.getUid(), user.getURole());
                    Map<String, String> tokenMap = new HashMap<>();
                    tokenMap.put("token", token);
                    return Mono.just(ResponseEntity.ok(new ApiResponse<>(MessageUtil.SUCCESS, HttpStatus.OK.value(),tokenMap)));
                })
                .switchIfEmpty(Mono.error(new RecordNotFounException(MessageUtil.USER_NOT_FOUND)));
    }
    @Override
    public Mono<User> findById(Integer id) {
        return userRepository.findById(id).switchIfEmpty(Mono.error(new RecordNotFounException(MessageUtil.RECORD_NOT_FOUND)));
    }

    @Override
    public Mono<TokenDetails> isTokenValid(String token, String currentEndpoint) {
        return Mono.just(AppUtils.decodeToken(token, currentEndpoint));
    }

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
