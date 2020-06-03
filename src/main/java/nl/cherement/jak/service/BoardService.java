package nl.cherement.jak.service;

import nl.cherement.jak.entity.BoardEntity;
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
    BoardRepository boardRepository;

    @Autowired
    UserService userService;

    public BoardService(BoardRepository repository) {
        super(repository);
    }

    public List<BoardEntity> findByUserName(String username) {
        return boardRepository.findByUsers_Username(username);
    }
    public BoardEntity addUser(Authentication authentication, Long boardId, UserEntity user) {

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User does not exists");
        }

        Optional<BoardEntity> board = boardRepository.findById(boardId);
        if (!board.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        BoardEntity boardEntity = board.get();
        boardEntity.users.add(user);

        return save(authentication,boardEntity);
    }

    @Override
    boolean hasAccess(Principal user, BoardEntity obj) {
        boolean userAcces = false;
        for (UserEntity userEntity : obj.users) {
            if (userEntity.username.equals(user.getName())) {
                userAcces = true;
                break;
            }
        }
        return userAcces;
    }
}