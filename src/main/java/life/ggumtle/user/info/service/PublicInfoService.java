package life.ggumtle.user.info.service;

import life.ggumtle.user.common.entity.Survey;
import life.ggumtle.user.common.repository.SurveyRepository;
import life.ggumtle.user.common.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PublicInfoService {

    private final UserRepository userRepository;
    private final SurveyRepository surveyRepository;

    public Mono<Boolean> checkNicknameDuplicate(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    public Mono<List<String>> getSurveyColumns() {
        List<String> columns = Arrays.asList(
                "환경",
                "자선활동",
                "인간관계",
                "휴식",
                "연애",
                "운동",
                "여행",
                "언어",
                "문화",
                "도전",
                "취미",
                "직장"
        );
        return Mono.just(columns);
    }
}
