package study.bulletinboard.config.exception;

import lombok.*;
import study.bulletinboard.common.constants.CustomErrorCode;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomErrorResponse {
    private String code;
    private String message;
}
