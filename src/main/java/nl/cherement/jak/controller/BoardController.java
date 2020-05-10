package nl.cherement.jak.controller;

import nl.cherement.jak.entity.BoardEntity;
import nl.cherement.jak.exception.RecordNotFoundException;
import nl.cherement.jak.model.BoardModel;
import nl.cherement.jak.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
public class BoardController {
    @Autowired
    BoardService service;
 
    @GetMapping
    public ResponseEntity<List<BoardEntity>> all() {
        List<BoardEntity> list = service.all();
 
        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<BoardEntity> single(@PathVariable("id") Long id) throws RecordNotFoundException {
        BoardEntity board = service.single(id);
 
        return new ResponseEntity<>(board, new HttpHeaders(), HttpStatus.OK);
    }
 
    @PostMapping
    public ResponseEntity<BoardEntity> update(BoardModel board) throws RecordNotFoundException {
        BoardEntity updatedBoard = service.update(board);

        return new ResponseEntity<>(updatedBoard, new HttpHeaders(), HttpStatus.OK);
    }
 
    @DeleteMapping("/{id}")
    public HttpStatus remove(@PathVariable("id") Long id) throws RecordNotFoundException {
        service.remove(id);

        return HttpStatus.FORBIDDEN;
    }
}