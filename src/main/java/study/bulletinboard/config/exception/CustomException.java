package study.bulletinboard.config.exception;

import lombok.Getter;
import study.bulletinboard.common.constants.CustomErrorCode;

public class CustomException extends RuntimeException {
    @Getter
    private CustomErrorCode customErrorCode;
    @Getter
    private String errorMessage;

    public CustomException(CustomErrorCode customErrorCode) {
        super(customErrorCode.getMessage());
        this.customErrorCode = customErrorCode;
        this.errorMessage = customErrorCode.getMessage();
    }
}
