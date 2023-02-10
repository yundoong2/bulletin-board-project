package study.bulletinboard.services.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import study.bulletinboard.entity.BoardEntity;

import java.sql.Date;
import java.time.LocalDateTime;


@Getter
@Setter
@ToString @Builder
public class BoardDto {
    private Long id;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime regDate;
    private Long hit;


}
