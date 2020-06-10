package nl.cherement.jak.controller;

import nl.cherement.jak.entity.BoardEntity;
import nl.cherement.jak.entity.EventEntity;
import nl.cherement.jak.entity.TeamEntity;
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
    public List<BoardEntity> findAll(Authentication authentication) {
        return service.findAll(authentication);
    }

    @GetMapping("/{id}")
    public Optional<BoardEntity> findById(Authentication authentication, @PathVariable Long id) {
        return service.findById(authentication, id);
    }

    @PostMapping
    public BoardEntity save(Authentication authentication, @RequestBody BoardDTO boardDTO) {
        return service.save(authentication, boardDTO.toEntity());
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(Authentication authentication, @PathVariable("id") BoardEntity boardEntity) {
        service.delete(authentication, boardEntity);

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

    @PostMapping("/user/{board}/{user}")
    public BoardEntity addUser(Authentication authentication, @PathVariable("board") BoardEntity boardEntity,
                               @PathVariable("user") UserEntity userEntity) {
        return service.addUser(authentication, boardEntity, userEntity);
    }

    @PostMapping("/team/{board}/{team}")
    public BoardEntity addTeam(Authentication authentication, @PathVariable("board") BoardEntity boardEntity,
                               @PathVariable("team") TeamEntity teamEntity) {
        return service.addTeam(authentication, boardEntity, teamEntity);
    }

    @GetMapping("/timeline/{board}")
    public List<EventEntity> getTimeline(Authentication authentication,
                                         @PathVariable("board") BoardEntity boardEntity) {
        return service.getTimeline(authentication, boardEntity);
    }

    @DeleteMapping("/user/{board}/{user}")
    public BoardEntity deleteUser(Authentication authentication, @PathVariable("board") BoardEntity boardEntity,
                                  @PathVariable("user") UserEntity userEntity) {
        return service.deleteUser(authentication, boardEntity, userEntity);
    }

    @DeleteMapping("/team/{board}")
    public BoardEntity deleteTeam(Authentication authentication, @PathVariable("board") BoardEntity boardEntity) {
        return service.deleteTeam(authentication, boardEntity);
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
