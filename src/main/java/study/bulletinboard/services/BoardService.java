package study.bulletinboard.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import study.bulletinboard.controller.dto.BoardInput;
import study.bulletinboard.entity.BoardEntity;
import study.bulletinboard.services.dto.BoardDto;
import study.bulletinboard.repository.BoardRepository;
import study.bulletinboard.common.utils.ParsingUtils;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository repository;

    /**
     * getBoardList 설명
     * <p>
     * - 게시글 전체 조회
     *
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
     * <p>
     * - 게시글 등록
     * <pre>
     * @param input {@link BoardDto}
     * @return BoardDto {@link BoardDto}
     * @throw
     * @author cyh68
     * @since 2023-02-10
     * </pre>
     **/
    @Transactional
    public BoardDto addBoardPost(BoardInput input) {
        //게시글 등록 시 초기 조회수 0으로 설정
        input.setHit(0L);
        BoardDto dto = ParsingUtils.toDto(repository.save(ParsingUtils.toEntity(input)));
        return dto;
    }

    /**
     * getBoardPost 설명
     * <p>
     * - 특정 게시글 조회
     * <pre>
     * @param id {@link Long}
     * @return BoardDto {@link BoardDto}
     * @throw
     * @author cyh68
     * @since 2023-02-10
     * </pre>
     **/
    public BoardDto getBoardPost(Long id) {
        BoardDto dto = ParsingUtils.toDto(repository.findById(id).
                orElseThrow(() -> new EntityNotFoundException()));

        return dto;
    }

    /**
     * updateBoardPost 설명
     * <p>
     * - 게시글 수정 (전체 수정 - 제목 and 본문)
     * <pre>
     * @param id {@link Long}
     * @param input {@link BoardInput}
     * @return BoardDto {@link BoardDto}
     * @throw
     * @author cyh68
     * @since 2023-02-11
     * </pre>
     **/
    @Transactional
    public BoardDto updatePostPut(Long id, BoardInput input) {
        BoardDto dto = null;
        Optional<BoardEntity> board = repository.findById(id);

        //기존 리소스 존재하면 업데이트
        if (board.isPresent()) {
            BoardEntity boardEntity = board.get();
            boardEntity.setTitle(input.getTitle());
            boardEntity.setContent(input.getContent());
            boardEntity.setWriter(input.getWriter());
            boardEntity.setHit(input.getHit());

            dto = ParsingUtils.toDto(repository.save(boardEntity));
        }
        else {
            //존재하지 않으면 해당 새로 추가
            dto = ParsingUtils.toDto(repository.save(ParsingUtils.toEntity(input)));
        }
        return dto;
    }

    /**
     * updatePostPatch 설명
     * <p>
     * - 게시글 수정 (부분 수정 - 제목 or 본문)
     * <pre>
     * - Title, Content 중 Null이 아닌 것을 체크하여 Set
     * @param id {@link Long}
     * @param input {@link BoardInput}
     * @return BoardDto {@link BoardDto}
     * @throw
     * @author cyh68
     * @since 2023-02-11
     * </pre>
     **/
    @Transactional
    public BoardDto updatePostPatch(Long id, BoardInput input) {
        BoardDto dto = null;
        Optional<BoardEntity> board = repository.findById(id);

        if (board.isPresent()) {
            BoardEntity boardEntity = board.get();

            if (StringUtils.hasText(input.getTitle()))
                boardEntity.setTitle(input.getTitle());
            if (StringUtils.hasText(input.getContent()))
                boardEntity.setContent(input.getContent());

            dto = ParsingUtils.toDto(repository.save(boardEntity));
        }
        return dto;
    }

    /**
     * deletePost 설명
     * <p>
     * - 특정 게시글 삭제
     * <pre>
     * @param id {@link Long}
     * @return ResponseEntity HttpStatus
     * @throw
     * @author cyh68
     * @since 2023-02-11
     * </pre>
     **/
    @Transactional
    public ResponseEntity<HttpStatus> deletePost(Long id) {
        ResponseEntity<HttpStatus> response;

        try {
            repository.deleteById(id);
            response = new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }
}
