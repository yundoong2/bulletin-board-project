package study.bulletinboard.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
/**
 * BoardInput 설명
 *
 * 클라이언트로부터 Controller에 request parameter를 전달받을 때 사용되는 Dto
 <pre>
 * @author cyh68
 * @since 2023-02-12
 </pre>
 **/
public class BoardInput {
    private Long id;
    private String title;
    private String content;
    private String writer;
    @JsonProperty("reg_date")
    private LocalDateTime regDate;
    private Long hit;
}
