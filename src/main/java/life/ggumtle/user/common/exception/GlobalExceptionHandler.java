package life.ggumtle.user.common.exception;

import life.ggumtle.user.common.response.Response;
import life.ggumtle.user.common.response.ResponseFail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = CustomException.class)
    protected ResponseEntity<Response> handleCustomException(CustomException e) {
        return ResponseEntity
                .status(e.getExceptionType().getCode())
                .body(new ResponseFail(String.valueOf(e.getExceptionType().getCode()), e.getMessage()));
    }
}
