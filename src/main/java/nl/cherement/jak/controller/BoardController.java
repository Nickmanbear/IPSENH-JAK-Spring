package nl.cherement.jak.controller;

import nl.cherement.jak.entity.BoardEntity;
import nl.cherement.jak.model.BoardModel;
import nl.cherement.jak.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    BoardService service;
 
    @GetMapping
    public List<BoardEntity> findAll() {
        return service.findAll();
    }
 
    @GetMapping("/{id}")
    public Optional<BoardEntity> findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }
 
    @PostMapping
    public BoardEntity save(@RequestBody BoardModel boardModel) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.importModal(boardModel);

        return service.save(boardEntity);
    }
 
    @DeleteMapping("/{id}")
    public HttpStatus deleteById(@PathVariable("id") Long id) {
        service.deleteById(id);

        return HttpStatus.OK;
    }
}