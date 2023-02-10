package study.bulletinboard.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import study.bulletinboard.controller.dto.BoardInput;
import study.bulletinboard.services.dto.BoardDto;
import study.bulletinboard.services.BoardService;

import java.util.List;

/**
 * BoardController 설명
 *
 <pre>
 * - 게시판 HTTP API 클래스
 * @author cyh68
 * @since 2023-02-08
</pre>
 **/
@Slf4j
@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    /**
     * addPost 설명
     *
     * 게시글 등록
     * @param input {@link BoardInput}
     * @return BoardDto {@link BoardDto}
     * @throw
     * @author cyh68
     * @since 2023-02-08
     **/
    @PostMapping(value = "/board/list")
    public BoardDto addPost(@RequestBody BoardInput input){
        BoardDto dto = boardService.addBoardPost(input);

        return dto;
    }

    /**
     * getList 설명
     *
     * 게시글 전체 조회
     * @return BoardDto List
     * @throw
     * @author cyh68
     * @since 2023-02-08
     **/
    @GetMapping(value = {"/", "/list", "/board/list"})
    public List<BoardDto> getAllPosts(){
        List<BoardDto> boardList = boardService.getBoardList();

        return boardList;
    }

    /**
     * getPost 설명
     *
     <pre>
     * 특정 게시글 조회
     * @param id {@link Long}
     * @return BoardDTO {@link BoardDto}
     * @throw 
     * @author cyh68
     * @since 2023-02-08
     </pre>
     **/
    @GetMapping(value = "/board/list/{id}")
    public BoardDto getPost(@PathVariable Long id){
        BoardDto dto = boardService.getBoardPost(id);

        return dto;
    }

    /**
     * editAllOfPost 설명
     *
     <pre>
     * 특정 게시물 내용 전체 수정
     * @param id {@link Long}
     * @param request {@link BoardDto}
     * @return BoardDTO {@link BoardDto}
     * @throw
     * @author cyh68
     * @since 2023-02-08
     </pre>
     **/
    @PutMapping(value = "/board/list/{id}")
    public String editAllOfPost(@PathVariable Long id, @RequestBody BoardDto request){
        return "redirect:/board/list";
    }

    /**
     * editPartOfPost 설명
     *
     <pre>
     * 특정 게시물 내용 부분 수정
     *  @param id {@link Long}
     *  @param request {@link BoardDto}
     * @return BoardDTO {@link BoardDto}
     * @throw 
     * @author cyh68
     * @since 2023-02-08
     </pre>
     **/
    @PatchMapping(value = "/board/list/{id}")
    public String editPartOfPost(@PathVariable Long id, @RequestBody BoardDto request){
        return "redirect:/board/list";
    }

    /**
     * deletePost 설명
     *
     <pre>
     * 특정 게시물 삭제
     * @param id {@link Long}
     * @return 
     * @throw 
     * @author cyh68
     * @since 2023-02-08
     </pre>
     **/
    @DeleteMapping(value = "/board/list/{id}")
    public String deletePost(@PathVariable Long id){
        return "redirect:/board/list";
    }

    

}
