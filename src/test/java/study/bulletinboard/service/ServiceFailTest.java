package study.bulletinboard.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import study.bulletinboard.BulletinBoardApplicationTests;
import study.bulletinboard.common.Constants;
import study.bulletinboard.config.exception.BadRequestException;
import study.bulletinboard.config.exception.CustomException;
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
class ServiceFailTest {
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
    @DisplayName("특정 게시글 조회 실패 테스트")
    public void getBoardPostTest_fail() {
        //Given
        Long id = Constants.TEST_FAIL_ID;

        //When
        Throwable exception = assertThrows(BadRequestException.class, () ->{
            boardService.getBoardPost(id);
        });

        //Then
        BadRequestException e = (BadRequestException) exception;
        //exception 안에 code와 message는 어떤식으로 넣어야 할까?
//        assertEquals("", e.getCode());
//        assertEquals("", e.getMessage());
    }

    @Test
    @DisplayName("게시글 등록 실패 테스트")
    public void addBoardPostTest_fail() {
        //Given
        BoardInput input = new BoardInput();
        input.setTitle("");
        input.setContent(Constants.TEST_CONTENT);
        input.setWriter(Constants.TEST_WRITER);
        input.setHit(Constants.TEST_HIT);

        //When
        Throwable exception = assertThrows(BadRequestException.class, () ->{
            boardService.addBoardPost(input);
        });

        //Then
        BadRequestException e = (BadRequestException) exception;
    }

    @Test
    @DisplayName("게시글 전체수정 실패 테스트")
    public void updatePostPutTest_fail() {
        //Given
        Long id = Constants.TEST_ID;

        BoardInput input = new BoardInput();
        input.setTitle("");
        input.setContent(Constants.TEST_CONTENT);
        input.setWriter(Constants.TEST_WRITER);
        input.setHit(Constants.TEST_HIT);

        //When
        Throwable exception = assertThrows(BadRequestException.class, () ->{
            boardService.updatePostPut(id, input);
        });

        //Then
        BadRequestException e = (BadRequestException) exception;
    }

    @Test
    @DisplayName("게시글 전체수정 실패 테스트")
    public void updatePostPatchTest_fail() {
        //Given
        Long id = Constants.TEST_ID;

        BoardInput input = new BoardInput();
        input.setTitle("");
        input.setContent(Constants.TEST_CONTENT);
        input.setWriter(Constants.TEST_WRITER);

        //When
        Throwable exception = assertThrows(BadRequestException.class, () ->{
            boardService.updatePostPatch(id, input);
        });

        //Then
        BadRequestException e = (BadRequestException) exception;
    }

    @Test
    @DisplayName("게시글 삭제 실패 테스트")
    public void deletePostTest() {
        //Given
        Long id = Constants.TEST_FAIL_ID;

        //When
        Throwable exception = assertThrows(CustomException.class, () ->{
            boardService.deletePost(id);
        });

        //Then
        CustomException e = (CustomException) exception;
    }

}
