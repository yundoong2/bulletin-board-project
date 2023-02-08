package study.bulletinboard.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;


@Getter @Setter
public class BoardDTO {
    private long id;
    private String title;
    private String content;
    private String writer;
    private Date regDate;
    private long hits;
}
