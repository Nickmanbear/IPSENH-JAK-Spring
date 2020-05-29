package nl.cherement.jak.service;

import nl.cherement.jak.entity.BoardEntity;
import nl.cherement.jak.entity.UserEntity;
import nl.cherement.jak.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

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

    public List<BoardEntity> findBoardByUserName(String username) {
        return boardRepository.findByUsers_Username(username);
    }

    public BoardEntity addUser(Authentication authentication, Long boardId, Long userId) {
        BoardEntity board = boardRepository.getOne(boardId);
        Optional<UserEntity> optionalUser = userService.findById(authentication,userId);
        optionalUser.ifPresent(userEntity -> board.users.add(userEntity));

        return boardRepository.save(board);
    }

    @Override
    boolean hasAccess(Principal user, BoardEntity obj) {
       boolean userAcces = false;
        for (UserEntity userEntity : obj.users) {
            if(userEntity.username.equals(user.getName())){
                userAcces = true;
               break;
            }
        }

        return userAcces;


    }
}