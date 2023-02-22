package study.bulletinboard.common.constants;

import lombok.Getter;

public enum CustomErrorCode {
    INVALID_REQUEST_ID_ERROR("E001", "필수 입력 값이 누락 되었습니다."),
    INVALID_REQUEST_BODY_ERROR("E002", "Request Body 입력 값이 누락 되었습니다."),
    BAD_REQUEST_ERROR("E003", "Bad Request Error 발생"),
    INTERNAL_SERVER_ERROR("", "Internal Server Error 발생");

    @Getter
    private final String message;

    @Getter
    private final String code;

    CustomErrorCode(String code, String message){
        this.code = code;
        this.message = message;
    }
}
