package study.bulletinboard.config.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import study.bulletinboard.common.constants.CustomErrorCode;
import study.bulletinboard.controller.dto.BaseResponse;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.net.BindException;
import java.util.NoSuchElementException;

/**
 * CustomExceptionHandler 설명
 * <p>
 * - 각 Exception에 대한 Handler 정의
 *
 * @author cyh68
 * @since 2023-02-23
 **/
@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public CustomErrorResponse handlerException(CustomException e, HttpServletRequest request) {

        log.error(e.getMessage(), e);

        return CustomErrorResponse.builder()
                .code(e.getCode())
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public CustomErrorResponse handlerException(EntityNotFoundException e, HttpServletRequest request) {

        log.error(e.getMessage(), e);

        return CustomErrorResponse.builder()
                .code(CustomErrorCode.BAD_REQUEST_ERROR.getCode())
                .message(CustomErrorCode.BAD_REQUEST_ERROR.getMessage())
                .build();
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public BaseResponse handlerException(BadRequestException e, HttpServletRequest request) {
        log.error(e.getMessage(), e);

        String message = CustomErrorCode.BAD_REQUEST_ERROR.getMessage();
        if (StringUtils.hasText(e.getMessage())) {
            message = e.getMessage();
        }

        return new BaseResponse(CustomErrorCode.BAD_REQUEST_ERROR.getCode(), message);
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public BaseResponse handlerException(BindException e, HttpServletRequest request) {
        log.error(e.getMessage(), e);

        String message = CustomErrorCode.BAD_REQUEST_ERROR.getMessage();
        if (StringUtils.hasText(e.getMessage())) {
            message = e.getMessage();
        }

        return new BaseResponse(CustomErrorCode.BAD_REQUEST_ERROR.getCode(), message);
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public BaseResponse handlerException(NoSuchElementException e, HttpServletRequest request) {
        log.error(e.getMessage(), e);

        String message = CustomErrorCode.BAD_REQUEST_ERROR.getMessage();
        if (StringUtils.hasText(e.getMessage())) {
            message = e.getMessage();
        }

        return new BaseResponse(CustomErrorCode.BAD_REQUEST_ERROR.getCode(), message);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public BaseResponse handlerException(MethodArgumentNotValidException e, HttpServletRequest request) {
        log.error(e.getMessage(), e);

        String message = CustomErrorCode.BAD_REQUEST_ERROR.getMessage();
//        if (StringUtils.hasText(e.getMessage())) {
//            message = e.getMessage();
//        }

        return new BaseResponse(CustomErrorCode.BAD_REQUEST_ERROR.getCode(), message);
    }
}
