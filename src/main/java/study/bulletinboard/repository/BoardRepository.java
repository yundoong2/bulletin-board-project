package study.bulletinboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.bulletinboard.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    /*  JpaRepository 에서 기본적으로 제공하는 메소드 외에 필요한 부분은 아래처럼 정의해서 사용 가능
    public BoardEntity findByTitle(String title);
    public BoardEntity findByTitleAndWriter(String title, String writer);
    */
}
