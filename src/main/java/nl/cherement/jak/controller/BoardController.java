package nl.cherement.jak.controller;

import nl.cherement.jak.entity.BoardEntity;
import nl.cherement.jak.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/board")
@MessageMapping("/board")
public class BoardController {

    @Autowired
    BoardService service;

    @GetMapping
    public List<BoardEntity> findAll(Authentication principal) {
        return service.findAll(principal);
    }

    @GetMapping("/{id}")
    public Optional<BoardEntity> findById(@PathVariable("id") Long id, Authentication principal) {
        return service.findById(principal, id);
    }

    @PostMapping
    public BoardEntity save(@RequestBody BoardDTO boardDTO) {
        return service.save(boardDTO.toEntity());
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteById(Authentication authentication, @PathVariable("id") Long id) {
        service.deleteById(authentication,id);

        return HttpStatus.OK;
    }

    @MessageMapping("/{id}")
    public boolean cardMoved() {
        return true;
    }

    @PostMapping("/user/{boardId}/{userId}")
    public BoardEntity addUser(Authentication authentication, @PathVariable("boardId") Long boardId,
                               @PathVariable("userId") Long userId) {
        return service.addUser(authentication,boardId, userId);
    }
}

class BoardDTO extends BoardEntity {

    BoardEntity toEntity() {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.id = id;
        boardEntity.users = users;
        boardEntity.name = name;

        return boardEntity;
    }
}
