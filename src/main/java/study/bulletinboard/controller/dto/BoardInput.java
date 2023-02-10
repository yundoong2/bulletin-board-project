package study.bulletinboard.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BoardInput {
    private Long id;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime regDate;
    private Long hit;
}
