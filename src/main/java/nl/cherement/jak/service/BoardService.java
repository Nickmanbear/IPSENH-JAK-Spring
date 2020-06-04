package nl.cherement.jak.service;

import nl.cherement.jak.entity.BoardEntity;
import nl.cherement.jak.entity.TeamEntity;
import nl.cherement.jak.entity.EventEntity;
import nl.cherement.jak.entity.UserEntity;
import nl.cherement.jak.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
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

    public List<BoardEntity> findByTeam(Long teamId) {
        Optional<TeamEntity> optionalTeam = teamService.findById(teamId);

        return optionalTeam.map(teamEntity -> boardRepository.findByTeam(teamEntity)).orElse(null);
    }

    public BoardEntity addUser(Authentication authentication, Long boardId, UserEntity user) {
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User does not exists");
        }

        Optional<BoardEntity> board = repository.findById(boardId);
        if (!board.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        BoardEntity boardEntity = board.get();
        boardEntity.users.add(user);

        return save(authentication,boardEntity);
    }

    public BoardEntity deleteUser(Long teamId, Long memberId) {
        Optional<BoardEntity> optionalBoard = boardRepository.findById(teamId);
        Optional<UserEntity> optionalUser = userService.findById(memberId);
        if (optionalBoard.isPresent() && optionalUser.isPresent()) {
            optionalBoard.get().users.remove(optionalUser.get());

            return boardRepository.save(optionalBoard.get());
        }
        return null;
    }
}