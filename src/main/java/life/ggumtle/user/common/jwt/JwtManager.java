package life.ggumtle.user.common.jwt;

import life.ggumtle.user.common.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtManager {

    private static final Logger logger = LoggerFactory.getLogger(JwtManager.class);

    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;

    @Value("${spring.jwt.secret.access}")
    private String accessSecretKey;

    public Mono<String> checkAccessToken(ServerWebExchange exchange) {
        return Mono.justOrEmpty(exchange.getRequest().getCookies().getFirst("ACCESS_TOKEN"))
                .flatMap(cookie -> tokenRepository.getToken(cookie.getValue()))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Access token not found or invalid")));
    }

    public Mono<Authentication> getAuthentication(String internalId) {
        return userRepository.findByInternalId(internalId)
                .map(userDetails -> (Authentication) new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found")));
    }
}