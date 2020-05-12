package nl.cherement.jak.controller;

import nl.cherement.jak.entity.BoardEntity;
import nl.cherement.jak.model.BoardModel;
import nl.cherement.jak.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    BoardService service;
 
    @GetMapping
    public List<BoardEntity> all() {
        return service.all();
    }
 
    @GetMapping("/{id}")
    public Optional<BoardEntity> single(@PathVariable("id") Long id) {
        return service.single(id);
    }
 
    @PostMapping
    public BoardEntity update(BoardModel boardModel) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.importModal(boardModel);

        return service.update(boardEntity);
    }
 
    @DeleteMapping("/{id}")
    public HttpStatus remove(@PathVariable("id") Long id) {
        service.remove(id);

        return HttpStatus.FORBIDDEN;
    }
}