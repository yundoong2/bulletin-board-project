package study.bulletinboard.config.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public CustomErrorResponse handlerException(CustomException e, HttpServletRequest request) {
        log.error("errorCode: {}, url: {}, message: {}",
                e.getCustomErrorCode(), request.getRequestURI(), e.getErrorMessage());

        return CustomErrorResponse.builder()
                .status(e.getCustomErrorCode())
                .statusMessage(e.getErrorMessage())
                .build();
    }
}
