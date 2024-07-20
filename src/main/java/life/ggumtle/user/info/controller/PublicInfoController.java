package life.ggumtle.user.info.controller;

import life.ggumtle.user.info.service.PublicInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/public")
@RequiredArgsConstructor
public class PublicInfoController {

    private final PublicInfoService publicInfoService;

    @GetMapping("/duplicate-nickname")
    public Mono<ResponseEntity<String>> checkNickname(@RequestParam String nickname) {
        return publicInfoService.checkNicknameDuplicate(nickname)
                .flatMap(isDuplicate -> {
                    if (isDuplicate) {
                        return Mono.just(ResponseEntity.status(HttpStatus.CONFLICT).body("Nickname is already in use"));
                    } else {
                        return Mono.just(ResponseEntity.ok("Nickname is available"));
                    }
                });
    }

    @GetMapping("/survey-columns")
    public Mono<ResponseEntity<List<String>>> getSurveyColumns() {
        return publicInfoService.getSurveyColumns()
                .map(columns -> ResponseEntity.ok().body(columns));
    }
}
