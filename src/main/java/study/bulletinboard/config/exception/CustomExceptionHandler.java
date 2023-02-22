package study.bulletinboard.config.exception;

import java.net.BindException;
import javax.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import study.bulletinboard.common.constants.CustomErrorCode;
import study.bulletinboard.controller.dto.BaseResponse;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

//    @ExceptionHandler(CustomException.class)
//    public CustomErrorResponse handlerException(CustomException e, HttpServletRequest request) {
//        log.error("errorCode: {}, url: {}, message: {}",
//                e.getCustomErrorCode(), request.getRequestURI(), e.getErrorMessage());
//
//        return CustomErrorResponse.builder()
//                .status(e.getCustomErrorCode())
//                .statusMessage(e.getErrorMessage())
//                .build();
//    }
//
//    @ExceptionHandler(EntityNotFoundException.class)
//    public CustomErrorResponse handlerException(EntityNotFoundException e, HttpServletRequest request) {
////        log.error("errorCode: {}, url: {}, message: {}",
////            e.getCustomErrorCode(), request.getRequestURI(), e.getErrorMessage());
//
//        return CustomErrorResponse.builder()
//            .code(CustomErrorCode.BAD_REQUEST_ERROR.getCode())
//            .status(CustomErrorCode.BAD_REQUEST_ERROR)
//            .statusMessage(CustomErrorCode.BAD_REQUEST_ERROR.getMessage())
//            .build();
//    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public BaseResponse handlerException(BadRequestException e, HttpServletRequest request) {
        String message = CustomErrorCode.BAD_REQUEST_ERROR.getMessage();
        if(!e.getMessage().isEmpty()) {
            message = e.getMessage();
        }

        return new BaseResponse(
            CustomErrorCode.BAD_REQUEST_ERROR.getCode(),
            message
        );
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public BaseResponse handlerException(BindException e, HttpServletRequest request) {
        String message = CustomErrorCode.BAD_REQUEST_ERROR.getMessage();
        if(!e.getMessage().isEmpty()) {
            message = e.getMessage();
        }

        return new BaseResponse(
            CustomErrorCode.BAD_REQUEST_ERROR.getCode(),
            message
        );
    }


}
