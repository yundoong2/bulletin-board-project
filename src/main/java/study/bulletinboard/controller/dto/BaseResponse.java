package study.bulletinboard.controller.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import study.bulletinboard.services.dto.BoardDto;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class BaseResponse implements Serializable {
    private String message;
    private Object data;

    public BaseResponse() {
        this.setMessage("Success");
    }

    public BaseResponse success(Object data) {
        this.setMessage("Success");
        this.setData(data);
        return this;
    }
}
