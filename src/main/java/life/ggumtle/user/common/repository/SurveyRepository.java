package life.ggumtle.user.common.repository;

import life.ggumtle.user.common.entity.Survey;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface SurveyRepository extends ReactiveCrudRepository<Survey, Long> {
}
