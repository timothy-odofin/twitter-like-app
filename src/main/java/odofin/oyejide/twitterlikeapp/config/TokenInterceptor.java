package odofin.oyejide.twitterlikeapp.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import odofin.oyejide.twitterlikeapp.service.UserManagementService;
import odofin.oyejide.twitterlikeapp.utils.JsonUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class TokenInterceptor implements WebFilter {
private final UserManagementService userManagementService;
    /**
     * Custom WebFilter for handling authentication and authorization based on API tokens.
     *
     * @param exchange The ServerWebExchange representing the current HTTP request and response.
     * @param chain The WebFilterChain for continuing the filter chain processing.
     * @return A Mono representing the completion of the filtering process.
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        // Extract the API token from the request headers
        String token = exchange.getRequest().getHeaders().getFirst("api_key");

        // Get the requested endpoint from the URI path
        String requestedEndpoint = exchange.getRequest().getURI().getPath();

        // Allow access to 'login' and 'webjars' endpoints without token validation
        if (requestedEndpoint.contains("login") || requestedEndpoint.contains("webjars")) {
            return chain.filter(exchange);
        }

        // Validate the API token with the UserManagementService
        return userManagementService.isTokenValid(token, requestedEndpoint)
                .flatMap(tokenDetails -> {
                    if (tokenDetails != null && tokenDetails.isAuthorizedUser() && tokenDetails.isTokenValid()) {
                        // If token is valid and user is authorized, continue with filter chain
                        return chain.filter(exchange);
                    } else {
                        // If token is invalid or user is unauthorized, prepare an error response
                        ServerHttpResponse response = exchange.getResponse();
                        response.setStatusCode(HttpStatus.valueOf(tokenDetails.getCode()));
                        response.getHeaders().add("Content-Type", "application/json");

                        // Write the error message to the response body
                        return response.writeWith(Mono.just(response.bufferFactory().wrap(
                                Objects.requireNonNull(JsonUtils.toJsonSafe(tokenDetails)).getBytes(StandardCharsets.UTF_8)
                        )));
                    }
                });
    }


}
