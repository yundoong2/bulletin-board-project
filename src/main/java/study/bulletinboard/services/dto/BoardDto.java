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
/**
 * BoardDto 설명
 *
 * board 데이터를 Service 및 Controller에서 전달 용도로 사용
 <pre>
 * @author cyh68
 * @since 2023-02-12
 </pre>
 **/
public class BoardDto {
    //게시글 ID
    private Long id;
    //게시글 제목
    private String title;
    //게시글 본문
    private String content;
    //게시글 작성자
    private String writer;
    //게시글 등록일
    private LocalDateTime regDate;
    //게시글 조회수
    private Long hit;

}
