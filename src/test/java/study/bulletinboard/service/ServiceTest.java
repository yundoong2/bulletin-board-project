package study.bulletinboard.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import study.bulletinboard.BulletinBoardApplicationTests;
import study.bulletinboard.common.Constants;
import study.bulletinboard.common.utils.ParsingUtils;
import study.bulletinboard.controller.dto.BoardInput;
import study.bulletinboard.services.BoardService;
import study.bulletinboard.services.dto.BoardDto;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = BulletinBoardApplicationTests.class)
@ActiveProfiles({"test"})
@TestMethodOrder(MethodOrderer.DisplayName.class)
@Transactional
class ServiceTest {
    @Autowired
    BoardService boardService;

    @BeforeEach
    public void beforeEach() {
        BoardInput input = new BoardInput();
        input.setTitle(Constants.TEST_TITLE);
        input.setContent(Constants.TEST_CONTENT);
        input.setWriter(Constants.TEST_WRITER);
        input.setHit(Constants.TEST_HIT);

        boardService.addBoardPost(input);
    }

    @Test
    @DisplayName("게시글 전체 조회 성공 테스트")
    public void getBoardListTest() {
        //Given
        //When
        List<BoardDto> response = boardService.getBoardList();

        //Then
        assertNotNull(response);
    }

    @Test
    @DisplayName("특정 게시글 조회 성공 테스트")
    public void getBoardPostTest() {
        //Given
        Long id = Constants.TEST_ID;

        //When
        BoardDto response = boardService.getBoardPost(id);

        //Then
        assertNotNull(response);
        assertEquals(response.getId(), Constants.TEST_ID);
    }

    @Test
    @DisplayName("게시글 등록 성공 테스트")
    public void addBoardPostTest() {
        //Given
        BoardInput input = new BoardInput();
        input.setTitle(Constants.TEST_TITLE);
        input.setContent(Constants.TEST_CONTENT);
        input.setWriter(Constants.TEST_WRITER);
        input.setHit(Constants.TEST_HIT);

        //When
        BoardDto response = boardService.addBoardPost(input);

        //Then
        assertNotNull(response);
        assertNotNull(response.getId());
        assertEquals(response.getTitle(), Constants.TEST_TITLE);
        assertEquals(response.getContent(), Constants.TEST_CONTENT);
        assertEquals(response.getWriter(), Constants.TEST_WRITER);
        assertEquals(response.getHit(), Constants.TEST_HIT);
    }

    @Test
    @DisplayName("게시글 전체 수정 성공 테스트")
    public void updatePostPutTest() {
        //Given
        Long id = Constants.TEST_ID;

        BoardInput input = new BoardInput();
        input.setTitle(Constants.TEST_TITLE_EDITED);
        input.setContent(Constants.TEST_CONTENT_EDITED);
        input.setWriter(Constants.TEST_WRITER_EDITED);
        input.setHit(Constants.TEST_HIT_EDITED);

        //When
        BoardDto response = boardService.updatePostPut(id, input);

        //Given
        assertNotNull(response);
        assertEquals(response.getId(), Constants.TEST_ID);
        assertEquals(response.getTitle(), Constants.TEST_TITLE_EDITED);
        assertEquals(response.getContent(), Constants.TEST_CONTENT_EDITED);
        assertEquals(response.getWriter(), Constants.TEST_WRITER_EDITED);
        assertEquals(response.getHit(), Constants.TEST_HIT_EDITED);
    }

    @Test
    @DisplayName("게시글 부분 수정 성공 테스트")
    public void updatePostPatchTest() {
        //Given
        Long id = Constants.TEST_ID;

        BoardInput input = new BoardInput();
        input.setTitle(Constants.TEST_TITLE_EDITED);
        input.setContent(Constants.TEST_CONTENT_EDITED);

        //When
        BoardDto response = boardService.updatePostPatch(id, input);

        //Then
        assertNotNull(response);
        assertEquals(response.getId(), Constants.TEST_ID);
        assertEquals(response.getTitle(), Constants.TEST_TITLE_EDITED);
        assertEquals(response.getContent(), Constants.TEST_CONTENT_EDITED);
        assertEquals(response.getWriter(), Constants.TEST_WRITER);
    }

    @Test
    @DisplayName("게시글 삭제 성공 테스트")
    public void deletePostTest() {
        //Given
        Long id = Constants.TEST_ID;

        //When
        ResponseEntity<HttpStatus> response = boardService.deletePost(id);

        //Then
        assertNotNull(response);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getStatusCodeValue(), HttpStatus.OK.value());
    }

}
