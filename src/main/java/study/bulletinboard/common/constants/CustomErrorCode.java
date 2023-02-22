package study.bulletinboard.common.constants;

import lombok.Getter;

public enum CustomErrorCode {
    INVALID_REQUEST_ID_ERROR("필수 입력 값이 누락 되었습니다."),
    INVALID_REQUEST_BODY_ERROR("Request Body 입력 값이 누락 되었습니다."),
    BAD_REQUEST_ERROR("Bad Request Error 발생"),
    INTERNAL_SERVER_ERROR("Internal Server Error 발생");

    @Getter
    private final String message;

    CustomErrorCode(String message){
        this.message = message;
    }
}
