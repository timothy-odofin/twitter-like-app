package odofin.oyejide.twitterlikeapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import odofin.oyejide.twitterlikeapp.model.dto.request.LoginRequest;
import odofin.oyejide.twitterlikeapp.model.dto.response.ApiResponse;
import odofin.oyejide.twitterlikeapp.model.dto.response.LoginResponse;
import odofin.oyejide.twitterlikeapp.service.UserManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/login")
@Tag(description = "login", name = "LOGIN")
@RequiredArgsConstructor
public class LoginController {
private final UserManagementService userManagementService;
    @PostMapping
    @Operation(description = "Login to the account")
    Mono<ResponseEntity<ApiResponse<Map<String,String>>>> login(@Valid @RequestBody LoginRequest request) {
       return userManagementService.login(request);
    }
}
