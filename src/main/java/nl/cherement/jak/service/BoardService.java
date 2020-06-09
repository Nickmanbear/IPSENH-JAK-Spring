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

import java.util.ArrayList;
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

    String unauthorizedMessage = "You do not have access to the object with id ";

    public BoardService(BoardRepository repository) {
        super(repository);
    }

    public List<BoardEntity> findByUserName(String username) {
        return repository.findByUsers_Username(username);
    }

    @Override
    public BoardEntity save(Authentication authentication, BoardEntity boardEntity) {
        if (boardEntity.id == 0L) {
            boardEntity.users = new ArrayList<>();
            boardEntity.users.add(userService.findByUsername(authentication.getName()));
        }
        return super.save(authentication, boardEntity);
    }

    public BoardEntity addUser(Authentication authentication, BoardEntity boardEntity, UserEntity userEntity) {
        if (!hasAccess(authentication, boardEntity)) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    unauthorizedMessage + boardEntity.id);
        }

        boardEntity.users.add(userEntity);

        return save(authentication, boardEntity);
    }

    public List<BoardEntity> findByTeam(Authentication authentication, Long teamId) {
        Optional<TeamEntity> optionalTeam = teamService.findById(authentication, teamId);

        return optionalTeam.map(teamEntity -> repository.findByTeam(teamEntity)).orElse(null);
    }

    public BoardEntity addTeam(Authentication authentication, BoardEntity board, TeamEntity team) {
        if (team == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Team does not exists");
        }
        board.team = team;

        return save(authentication, board);
    }

    public List<EventEntity> getTimeline(Authentication authentication, BoardEntity boardEntity) {
        if (!hasAccess(authentication, boardEntity)) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    unauthorizedMessage + boardEntity.id);
        }
        return eventService.getByBoard(boardEntity);
    }


    public BoardEntity deleteUser(Authentication authentication, BoardEntity boardEntity, UserEntity member) {
        if (!hasAccess(authentication, boardEntity)) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    unauthorizedMessage + boardEntity.id);
        }
        boardEntity.users.remove(member);

        return repository.save(boardEntity);
    }

    public BoardEntity deleteTeam(BoardEntity boardEntity) {
        boardEntity.team = null;

        return repository.save(boardEntity);
    }

    @Override
    boolean hasAccess(Authentication authentication, BoardEntity boardEntity) {
        UserEntity requestingUser = userService.findByUsername(authentication.getName());

        for (UserEntity boardMember : boardEntity.users) {
            if (boardMember.id.equals(requestingUser.id)) {
                return true;
            }
        }

        if (boardEntity.team != null) {
            for (UserEntity teamMember : boardEntity.team.members) {
                if (teamMember.id.equals(requestingUser.id)) {
                    return true;
                }
            }
        }
        return false;
    }
}