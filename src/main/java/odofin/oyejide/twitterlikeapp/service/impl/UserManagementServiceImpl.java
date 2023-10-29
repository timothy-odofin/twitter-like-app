package odofin.oyejide.twitterlikeapp.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import odofin.oyejide.twitterlikeapp.model.dto.request.LoginRequest;
import odofin.oyejide.twitterlikeapp.model.dto.response.LoginResponse;
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

@Service
@RequiredArgsConstructor
@Slf4j
public class UserManagementServiceImpl implements UserManagementService {
    private final UserRepository userRepository;

    @Override
    public Mono<ResponseEntity<LoginResponse>> login(LoginRequest request) {
        return userRepository.findByUNameAndUid(request.getUsername(), request.getId())
                .flatMap(user -> {
                    String token = AppUtils.generateToken(user.getUName(), user.getUid());
                    LoginResponse loginResponse = new LoginResponse(HttpStatus.OK.value(), token, MessageUtil.LOGIN_SUCCESSFUL);
                    return Mono.just(ResponseEntity.ok(loginResponse));
                })
                .switchIfEmpty(Mono.error(new RecordNotFounException(MessageUtil.USER_NOT_FOUND)));
    }

    @Override
    public Mono<User> findById(Integer id) {
        return userRepository.findById(id).switchIfEmpty(Mono.error(new RecordNotFounException(MessageUtil.RECORD_NOT_FOUND)));
    }
}
