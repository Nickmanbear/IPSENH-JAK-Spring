package nl.cherement.jak.service;

import nl.cherement.jak.entity.BoardEntity;
import nl.cherement.jak.entity.TeamEntity;
import nl.cherement.jak.entity.UserEntity;
import nl.cherement.jak.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService extends AbstractService<BoardEntity> {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    UserService userService;

    @Autowired
    TeamService teamService;

    public BoardService(BoardRepository repository) {
        super(repository);
    }

    public List<BoardEntity> findByUserName(String username) {
        return boardRepository.findByUsers_Username(username);
    }

    public List<BoardEntity> findByTeam(Long teamId) {
        Optional<TeamEntity> optionalTeam = teamService.findById(teamId);

        return optionalTeam.map(teamEntity -> boardRepository.findByTeam(teamEntity)).orElse(null);
    }

    public BoardEntity addUser(Long boardId, Long userId) {
        BoardEntity board = boardRepository.getOne(boardId);
        Optional<UserEntity> optionalUser = userService.findById(userId);
        optionalUser.ifPresent(userEntity -> board.users.add(userEntity));

        return boardRepository.save(board);
    }
}
