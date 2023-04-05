package com.zkiss.proman.model.DTO.boardcolumnDTO;

import com.zkiss.proman.model.BoardColumn;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateBoardColumnResponse {
    String componentType;

    Long boardId;

    BoardColumn boardColumn;
}
