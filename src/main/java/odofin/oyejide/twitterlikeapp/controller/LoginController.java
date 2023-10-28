package odofin.oyejide.twitterlikeapp.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import odofin.oyejide.twitterlikeapp.model.dto.request.LoginRequest;
import odofin.oyejide.twitterlikeapp.model.dto.response.LoginResponse;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/login")
@Api(tags = "login")
public class LoginController {

    @PostMapping
    @ApiOperation(value = "Login to account", notes = "Login to existing account")
    public Mono<LoginResponse> login(@RequestBody Mono<LoginRequest> request) {
        return null;
    }
}
