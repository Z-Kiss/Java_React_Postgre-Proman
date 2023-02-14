package com.zkiss.proman.controller;

import com.zkiss.proman.modal.Board;
import com.zkiss.proman.modal.DTO.boardDTO.BoardCreateRequest;
import com.zkiss.proman.service.BoardService;
import com.zkiss.proman.service.SessionService;
import org.apache.catalina.connector.Response;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController {

    private BoardService boardService;

    private SessionService sessionService;

    public BoardController(BoardService boardService, SessionService sessionService) {
        this.boardService = boardService;
        this.sessionService = sessionService;
    }

    @PostMapping("/create")
    public void createBoard(@RequestBody BoardCreateRequest createRequest){
        boardService.createBoard(createRequest);
    }

    @GetMapping("/get-all-boards")
    public List<Board> getAllBoards(){
        return boardService.getAllBoards();
    }

    @GetMapping("/get-all-boards-by-user")
    public List<Board> getAllBoardsByUser(){
        return boardService.getAllBoardsByUser(sessionService.get("userId"));
    }

    @GetMapping("/get-all-guest-boards")
    public List<Board> getAllBoardsByGuest(){
        return boardService.getAllBoardsByGuest();
    }

}