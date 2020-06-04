package nl.cherement.jak.controller;

import nl.cherement.jak.entity.BoardEntity;
import nl.cherement.jak.entity.EventEntity;
import nl.cherement.jak.entity.UserEntity;
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
    public Optional<BoardEntity> findById(Authentication principal, @PathVariable("id") Long id) {
        return service.findById(principal, id);
    }

    @PostMapping
    public BoardEntity save(Authentication authentication, @RequestBody BoardDTO boardDTO) {
        return service.save(authentication, boardDTO.toEntity());
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteById(Authentication authentication, @PathVariable("id") Long id) {
        service.deleteById(authentication, id);

        return HttpStatus.OK;
    }

    @MessageMapping("/{id}")
    public boolean cardMoved() {
        return true;
    }

    @GetMapping("/team/{id}")
    public List<BoardEntity> findByTeam(Authentication authentication, @PathVariable("id") Long id) {
        return service.findByTeam(authentication, id);
    }

    @PostMapping("/user/{boardId}/{userId}")
    public BoardEntity addUser(Authentication authentication, @PathVariable("boardId") Long boardId,
                               @PathVariable("userId") UserEntity user) {
        return service.addUser(authentication, boardId, user);
    }

    @GetMapping("/timeline/{boardId}")
    public List<EventEntity> getTimeline(Authentication authentication, @PathVariable("boardId") Long boardId) {
        return service.getTimeline(authentication, boardId);
    }

    @DeleteMapping("/user/{boardId}/{userId}")
    public BoardEntity deleteUser(Authentication authentication, @PathVariable("boardId") Long teamId,
                                  @PathVariable("userId") Long userId) {
        return service.deleteUser(authentication, teamId, userId);
    }
}

class BoardDTO extends BoardEntity {

    BoardEntity toEntity() {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.id = id;
        boardEntity.users = users;
        boardEntity.name = name;
        boardEntity.team = team;

        return boardEntity;
    }
}
