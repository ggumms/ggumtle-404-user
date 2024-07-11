package life.ggumtle.user.info.service;

import life.ggumtle.user.common.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PublicInfoService {

    private final UserRepository userRepository;

    public Mono<Boolean> checkNicknameDuplicate(String nickname) {
        return userRepository.existsByNickname(nickname);
    }
}
