package nl.cherement.jak.service;

import nl.cherement.jak.entity.BoardEntity;
import nl.cherement.jak.entity.EventEntity;
import nl.cherement.jak.entity.TeamEntity;
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
    TeamService teamService;
  
    @Autowired
    EventService eventService;

    public BoardService(BoardRepository repository) {
        super(repository);
    }

    public List<BoardEntity> findByUserName(String username) {
        return repository.findByUsers_Username(username);
    }

    public BoardEntity addUser(Authentication authentication, BoardEntity boardEntity, UserEntity userEntity) {
        if (!hasAccess(authentication, boardEntity)) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "You do not have access to the object with id " + boardEntity.id);
        }

        boardEntity.users.add(userEntity);

        return save(authentication,boardEntity);
    }

    public List<BoardEntity> findByTeam(Authentication authentication, Long teamId) {
        Optional<TeamEntity> optionalTeam = teamService.findById(authentication, teamId);

        return optionalTeam.map(teamEntity -> repository.findByTeam(teamEntity)).orElse(null);
    }

    public BoardEntity addTeam(Authentication authentication, Long boardId, TeamEntity team) {
        if (team == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Team does not exists");
        }
        Optional<BoardEntity> board = repository.findById(boardId);
        if (!board.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        BoardEntity boardEntity = board.get();
        boardEntity.team = team;

        return save(authentication,boardEntity);
    }

    public List<EventEntity> getTimeline(Authentication authentication, BoardEntity boardEntity) {
        if (!hasAccess(authentication, boardEntity)) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "You do not have access to the object with id " + boardEntity.id);
        }
        return eventService.getByBoard(boardEntity);
    }


    public BoardEntity deleteUser(Authentication authentication, Long boardId, Long memberId) {
        Optional<BoardEntity> optionalBoard = repository.findById(boardId);
        Optional<UserEntity> optionalUser = userService.findById(authentication, memberId);
        if (optionalBoard.isPresent() && optionalUser.isPresent()) {
            optionalBoard.get().users.remove(optionalUser.get());

            return repository.save(optionalBoard.get());
        }
        return null;
    }

    public BoardEntity deleteTeam(Long boardId) {
        Optional<BoardEntity> optionalBoard = repository.findById(boardId);
        if (optionalBoard.isPresent()) {
            optionalBoard.get().team = null;

            return repository.save(optionalBoard.get());
        }
        return null;
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