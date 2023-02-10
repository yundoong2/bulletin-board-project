package study.bulletinboard.common.utils;

import study.bulletinboard.controller.dto.BoardInput;
import study.bulletinboard.services.dto.BoardDto;
import study.bulletinboard.entity.BoardEntity;

public class ParsingUtils {
    /**
     * toEntity 설명
     *
     * DTO에서 Entity 변환
     * @param boardDto {@link BoardDto}
     * @return BoardEntity {@link BoardEntity}
     * @throw 
     * @author cyh68
     * @since 2023-02-10
     **/
    public static BoardEntity toEntity(BoardInput input) {
        BoardEntity board = BoardEntity.builder()
                .id(input.getId())
                .title(input.getTitle())
                .content(input.getContent())
                .writer(input.getWriter())
                .regDate(input.getRegDate())
                .hit(input.getHit())
                .build();

        return board;
    }

    /**
     * toDto 설명
     *
     * Entity에서 Dto 변환
     * @param entity {@link BoardEntity}
     * @return BoardDto {@link BoardDto}
     * @throw 
     * @author cyh68
     * @since 2023-02-10
     **/
    public static BoardDto toDto(BoardEntity entity) {
        BoardDto dto = BoardDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writer(entity.getWriter())
                .regDate(entity.getRegDate())
                .hit(entity.getHit())
                .build();
        return dto;
    }

}
