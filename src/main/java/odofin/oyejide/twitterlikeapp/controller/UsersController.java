package odofin.oyejide.twitterlikeapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import odofin.oyejide.twitterlikeapp.model.dto.request.AddUserRequest;
import odofin.oyejide.twitterlikeapp.model.dto.response.ApiResponse;
import odofin.oyejide.twitterlikeapp.model.dto.response.UserResponse;
import odofin.oyejide.twitterlikeapp.service.UserManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;

@RestController
@Tag(description = "Fetch all User ", name = "USERS")
@RequiredArgsConstructor
public class UsersController {
   private final UserManagementService userManagementService;
    @GetMapping("/users/{roleName}")
    @Operation(description = "Get all  users by a particular role",parameters = {
            @Parameter(name = "Users Role", in = ParameterIn.PATH, required = true, description = "Role name")
    })
    public Mono<ResponseEntity<ApiResponse<List<UserResponse>>>> getUsers(@PathVariable String roleName, @RequestHeader("api_key")String api_key) {
        return userManagementService.listUserByRoles(roleName);
    }

    @PostMapping("/user")
    @Operation(description = "Endpoint for adding new User")
   public Mono<ResponseEntity<ApiResponse<String>>> addUser(@Valid @RequestBody AddUserRequest request, @RequestHeader("api_key")String api_key){
        return userManagementService.addUser(request);

    }
}
