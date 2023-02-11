package study.bulletinboard.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "BOARD") //MySQL 8.x 기준으로 해당 버전 이상부터는 테이블 명을 무조건 대문자로 해야함
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(nullable = false, name = "TITLE")
    private String title;
    @Column(columnDefinition = "TEXT", nullable = false, name = "CONTENT")
    private String content;
    @Column(length = 20, nullable = false, name = "WRITER")
    private String writer;
    @Column(name = "CREATED_AT", nullable = false)
    @CreationTimestamp
    private LocalDateTime regDate = LocalDateTime.now();
    @Column(name = "HIT")
    private Long hit;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
