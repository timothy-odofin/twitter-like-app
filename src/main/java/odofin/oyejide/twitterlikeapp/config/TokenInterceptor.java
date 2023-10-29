package odofin.oyejide.twitterlikeapp.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import odofin.oyejide.twitterlikeapp.service.UserManagementService;
import odofin.oyejide.twitterlikeapp.utils.AppUtils;
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
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String token = exchange.getRequest().getHeaders().getFirst("api_key");
        String requestedEndpoint = exchange.getRequest().getURI().getPath();

        if (requestedEndpoint.contains("login"))
            return chain.filter(exchange);

        return userManagementService.isTokenValid(token, requestedEndpoint)
                .flatMap(tokenDetails -> {
                    if (tokenDetails != null && tokenDetails.isAuthorizedUser() && tokenDetails.isTokenValid()) {
                        return chain.filter(exchange);
                    } else {
                        ServerHttpResponse response = exchange.getResponse();
                        response.setStatusCode(HttpStatus.valueOf(tokenDetails.getCode()));
                        response.getHeaders().add("Content-Type", "application/json");
                        return response.writeWith(Mono.just(response.bufferFactory().wrap(Objects.requireNonNull(JsonUtils.toJsonSafe(tokenDetails)).getBytes(StandardCharsets.UTF_8))));

                    }
                });
    }

}
