package study.bulletinboard.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import study.bulletinboard.dto.BoardDTO;
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
@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;

    /**
     * addPost 설명
     *
     <pre>
     * 게시글 등록
     * @param
     * @return url {@link String}
     * @throw
     * @author cyh68
     * @since 2023-02-08
    </pre>
     **/
    @PostMapping(value = {})
    public String addPost(@RequestBody BoardDTO request){
        return "redirect:/board/list";
    }

    /**
     * getList 설명
     *
     <pre>
     * 게시글 전체 조회
     * @return BoardDTO List {@link List<BoardDTO//>}
     * @throw
     * @author cyh68
     * @since 2023-02-08
     </pre>
     **/
    @GetMapping(value = {"/", "/list", "/board/list"})
    public String getAllPosts(){
        return "/board/list";
    }

    /**
     * getPost 설명
     *
     <pre>
     * 특정 게시글 조회
     * @param id {@link Long}
     * @return BoardDTO {@link BoardDTO}
     * @throw 
     * @author cyh68
     * @since 2023-02-08
     </pre>
     **/
    @GetMapping(value = "/board/list/{id}")
    public String getPost(@PathVariable Long id){
        return "/board/detail";
    }

    /**
     * editAllOfPost 설명
     *
     <pre>
     * 특정 게시물 내용 전체 수정
     * @param id {@link Long}
     * @param request {@link BoardDTO}
     * @return BoardDTO {@link BoardDTO}
     * @throw
     * @author cyh68
     * @since 2023-02-08
     </pre>
     **/
    @PutMapping(value = "/board/list/{id}")
    public String editAllOfPost(@PathVariable Long id, @RequestBody BoardDTO request){
        return "redirect:/board/list";
    }

    /**
     * editPartOfPost 설명
     *
     <pre>
     * 특정 게시물 내용 부분 수정
     *  @param id {@link Long}
     *  @param request {@link BoardDTO}
     * @return BoardDTO {@link BoardDTO}
     * @throw 
     * @author cyh68
     * @since 2023-02-08
     </pre>
     **/
    @PatchMapping(value = "/board/list/{id}")
    public String editPartOfPost(@PathVariable Long id, @RequestBody BoardDTO request){
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
