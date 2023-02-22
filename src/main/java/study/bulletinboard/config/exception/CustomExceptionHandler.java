package study.bulletinboard.config.exception;

import javax.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import study.bulletinboard.common.constants.CustomErrorCode;

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

    @ExceptionHandler(EntityNotFoundException.class)
    public CustomErrorResponse handlerException(EntityNotFoundException e, HttpServletRequest request) {
//        log.error("errorCode: {}, url: {}, message: {}",
//            e.getCustomErrorCode(), request.getRequestURI(), e.getErrorMessage());

        return CustomErrorResponse.builder()
            .code(CustomErrorCode.BAD_REQUEST_ERROR.getCode())
            .status(CustomErrorCode.BAD_REQUEST_ERROR)
            .statusMessage(CustomErrorCode.BAD_REQUEST_ERROR.getMessage())
            .build();
    }
}
