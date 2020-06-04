package nl.cherement.jak.service;

import nl.cherement.jak.entity.BoardEntity;
import nl.cherement.jak.entity.EventEntity;
import nl.cherement.jak.entity.UserEntity;
import nl.cherement.jak.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService extends AbstractService<BoardEntity> {

    @Autowired
    BoardRepository repository;

    @Autowired
    UserService userService;

    @Autowired
    EventService eventService;

    public BoardService(BoardRepository repository) {
        super(repository);
    }

    public List<BoardEntity> findByUserName(String username) {
        return repository.findByUsers_Username(username);
    }

    public BoardEntity addUser(Authentication authentication, Long boardId, Long userId) {
        Optional<UserEntity> userOptional = userService.findById(authentication, userId);
        Optional<BoardEntity> boardOptional = repository.findById(boardId);

        if (!boardOptional.isPresent() || !userOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        BoardEntity boardEntity = boardOptional.get();
        boardEntity.users.add(userOptional.get());

        return save(authentication,boardEntity);
    }

    public List<EventEntity> getTimeline(Authentication authentication, Long boardId) {
        Optional<BoardEntity> boardOptional = repository.findById(boardId);

        if (!boardOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        if (!hasAccess(authentication, boardOptional.get())) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "You do not have access to the object with id " + boardId);
        }
        return eventService.getByBoardId(boardId);
    }

    @Override
    boolean hasAccess(Authentication authentication, BoardEntity entity) {
        boolean userAccess = false;
        for (UserEntity userEntity : entity.users) {
            if (userEntity.username.equals(authentication.getName())) {
                userAccess = true;
                break;
            }
        }
        return userAccess;
    }
}