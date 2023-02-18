package com.zkiss.proman.modal.DTO.boardcolumnDTO;

import com.zkiss.proman.modal.DTO.Validator;
import lombok.Data;

@Data
public class BoardColumnCreateRequest implements Validator {

    private Long boardId;

    private String boardColumnTitle;

    private String boardColumnColor;
}