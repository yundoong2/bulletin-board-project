package study.bulletinboard.config.exception;

import lombok.Getter;
import study.bulletinboard.common.constants.CustomErrorCode;

public class CustomException extends RuntimeException {
    @Getter
    private String code;
    @Getter
    private String message;


    public CustomException(CustomErrorCode customErrorCode) {
        super(customErrorCode.getMessage());
        this.code = customErrorCode.getCode();
        this.message = customErrorCode.getMessage();
    }
}
