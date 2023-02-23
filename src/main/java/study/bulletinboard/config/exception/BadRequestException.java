package study.bulletinboard.config.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BadRequestException extends RuntimeException {
    private String code;
    private String message;

    public BadRequestException() { }
    public BadRequestException(String message) {
        this.message = message;
    }
}
