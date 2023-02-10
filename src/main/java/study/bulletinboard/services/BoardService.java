package study.bulletinboard.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import study.bulletinboard.controller.dto.BoardInput;
import study.bulletinboard.entity.BoardEntity;
import study.bulletinboard.services.dto.BoardDto;
import study.bulletinboard.repository.BoardRepository;
import study.bulletinboard.common.utils.ParsingUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {
    private final BoardRepository repository;

    /**
     * getBoardList 설명
     * <p>
     * - 게시글 전체 조회
     * @return BoardDto List
     * @throw
     * @author cyh68
     * @since 2023-02-10
     **/
    public List<BoardDto> getBoardList() {
        List<BoardDto> dtoList = new ArrayList<>();

        for (BoardEntity entity : repository.findAll()) {
            BoardDto dto = ParsingUtils.toDto(entity);
            dtoList.add(dto);
        }
        return dtoList;
    }

    /**
     * addBoardPost 설명
     *
     * - 게시글 등록
     * <pre>
     * @param input {@link BoardDto}
     * @return BoardDto {@link BoardDto}
     * @throw
     * @author cyh68
     * @since 2023-02-10
     * </pre>
     **/
    public BoardDto addBoardPost(BoardInput input) {
        BoardDto dto = ParsingUtils.toDto(repository.save(ParsingUtils.toEntity(input)));
        return dto;
    }

    /**
     * getBoardPost 설명
     *
     * - 특정 게시물 조회
     <pre>
     * @param id {@link Long}
     * @return BoardDto {@link BoardDto}
     * @throw 
     * @author cyh68
     * @since 2023-02-10
     </pre>
     **/
    public BoardDto getBoardPost(Long id) {
        BoardDto dto = ParsingUtils.toDto(repository.findById(id).get());
        return dto;
    }



}
