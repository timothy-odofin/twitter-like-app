package odofin.oyejide.twitterlikeapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import odofin.oyejide.twitterlikeapp.model.dto.request.LoginRequest;
import odofin.oyejide.twitterlikeapp.model.dto.response.LoginResponse;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/login")
@Tag(description = "login", name = "LOGIN")
public class LoginController {

    @PostMapping
    @Operation(description = "Login to account")
    public Mono<LoginResponse> login(@RequestBody Mono<LoginRequest> request) {
        return Mono.just(new LoginResponse());
    }
}
