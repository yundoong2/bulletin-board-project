package study.bulletinboard.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import study.bulletinboard.config.annotation.CustomAnnotation;
import study.bulletinboard.config.annotation.TraceAnnotation;
import study.bulletinboard.controller.dto.BaseResponse;
import study.bulletinboard.controller.dto.BoardInput;
import study.bulletinboard.services.dto.BoardDto;
import study.bulletinboard.services.BoardService;

import javax.validation.Valid;
import java.util.List;

/**
 * BoardController 설명
 *
 * <pre>
 * - 게시판 HTTP API 클래스
 * @author cyh68
 * @since 2023-02-08
 * </pre>
 **/
@Slf4j
@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    /**
     * addPost 설명
     * <p>
     * 게시글 등록
     *
     * @param input {@link BoardInput}
     * @return BoardDto {@link BoardDto}
     * @throw
     * @author cyh68
     * @since 2023-02-08
     **/
    @PostMapping(value = "/board/list")
    @CustomAnnotation
    @TraceAnnotation
    public BaseResponse addPost(@RequestBody @Validated BoardInput input) {
        BoardDto result = boardService.addBoardPost(input);
        return new BaseResponse().success(result);
    }

    /**
     * getList 설명
     * <p>
     * 게시글 전체 조회
     *
     * @return BoardDto List
     * @throw
     * @author cyh68
     * @since 2023-02-08
     **/
    @GetMapping(value = {"/", "/list", "/board/list"})
    @TraceAnnotation
    public BaseResponse getAllPosts() {
        List<BoardDto> result = boardService.getBoardList();
        return new BaseResponse().success(result);
    }

    /**
     * getPost 설명
     *
     * <pre>
     * 특정 게시글 조회
     * @param id {@link Long}
     * @return BoardDto {@link BoardDto}
     * @throw
     * @author cyh68
     * @since 2023-02-08
     * </pre>
     **/
    @GetMapping(value = "/board/list/{id}")
    public BaseResponse getPost(@PathVariable Long id) {
        BoardDto result = boardService.getBoardPost(id);
        return new BaseResponse().success(result);
    }

    /**
     * editAllOfPost 설명
     *
     * <pre>
     * 특정 게시물 내용 전체 수정
     * @param id {@link Long}
     * @param input {@link BoardInput}
     * @return BoardDto {@link BoardDto}
     * @throw
     * @author cyh68
     * @since 2023-02-08
     * </pre>
     **/
    @PutMapping(value = "/board/list/{id}")
    @CustomAnnotation
    public BaseResponse editAllOfPost(@PathVariable Long id, @RequestBody @Validated BoardInput input) {
        BoardDto result = boardService.updatePostPut(id, input);

        return new BaseResponse().success(result);
    }

    /**
     * editPartOfPost 설명
     *
     * <pre>
     * 특정 게시물 내용 부분 수정
     *  @param id {@link Long}
     *  @param input {@link BoardInput}
     * @return BoardDto {@link BoardDto}
     * @throw
     * @author cyh68
     * @since 2023-02-08
     * </pre>
     **/
    @PatchMapping(value = "/board/list/{id}")
    public BaseResponse editPartOfPost(@PathVariable Long id, @RequestBody BoardInput input) {
        BoardDto result = boardService.updatePostPatch(id, input);

        return new BaseResponse().success(result);
    }

    /**
     * deletePost 설명
     *
     * <pre>
     * 특정 게시물 삭제
     * @param id {@link Long}
     * @return ResponseEntity HttpStatus
     * @throw
     * @author cyh68
     * @since 2023-02-08
     * </pre>
     **/
    @DeleteMapping(value = "/board/list/{id}")
    public BaseResponse deletePost(@PathVariable Long id) {
        ResponseEntity<HttpStatus> result = boardService.deletePost(id);

        return new BaseResponse().success(result);
    }


}
