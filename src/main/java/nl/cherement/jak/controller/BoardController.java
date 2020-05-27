package nl.cherement.jak.controller;

import nl.cherement.jak.entity.BoardEntity;
import nl.cherement.jak.entity.UserEntity;
import nl.cherement.jak.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    BoardService service;
 
    @GetMapping
    public List<BoardEntity> findAll(Principal principal) {
        return service.findByUserName(principal.getName());
    }
 
    @GetMapping("/{id}")
    public Optional<BoardEntity> findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }
 
    @PostMapping
    public BoardEntity save(@RequestBody BoardDTO boardDTO) {
        return service.save(boardDTO.toEntity());
    }
 
    @DeleteMapping("/{id}")
    public HttpStatus deleteById(@PathVariable("id") Long id) {
        service.deleteById(id);

        return HttpStatus.OK;
    }

    @PostMapping("/user/{id}")
    public HttpStatus addUser(@PathVariable("id") Long boardId, @RequestBody UserEntity user) {
        service.addUser(boardId, user.getId());

        return HttpStatus.OK;
    }
}

class BoardDTO extends BoardEntity {

    BoardEntity toEntity() {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setId(getId());
        boardEntity.setUsers(getUsers());
        boardEntity.setName(getName());

        return boardEntity;
    }
}