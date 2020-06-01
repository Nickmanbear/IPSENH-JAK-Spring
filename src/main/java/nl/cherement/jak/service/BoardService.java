package nl.cherement.jak.service;

import nl.cherement.jak.entity.BoardEntity;
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

    public BoardService(BoardRepository repository) {
        super(repository);
    }

    public List<BoardEntity> findByUserName(String username) {
        return boardRepository.findByUsers_Username(username);
    }

    public BoardEntity addUser(Long boardId, Long userId) {
        Optional<BoardEntity> optionalBoard = boardRepository.findById(boardId);
        Optional<UserEntity> optionalUser = userService.findById(userId);
        if (optionalBoard.isPresent() && optionalUser.isPresent()) {
            UserEntity userEntity = optionalUser.get();
            BoardEntity boardEntity = optionalBoard.get();
            boardEntity.users.add(userEntity);

            return boardRepository.save(boardEntity);
        }
        return null;
    }
}
