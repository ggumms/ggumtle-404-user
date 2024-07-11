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
        Field[] fields = Survey.class.getDeclaredFields();
        List<String> columns = Arrays.stream(fields)
                .map(Field::getName)
                .filter(fieldName -> !fieldName.equals("id") && !fieldName.equals("userId"))
                .collect(Collectors.toList());
        return Mono.just(columns);
    }
}
