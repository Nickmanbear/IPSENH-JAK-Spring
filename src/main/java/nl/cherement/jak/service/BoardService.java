package nl.cherement.jak.service;

import nl.cherement.jak.entity.BoardEntity;
import nl.cherement.jak.entity.UserEntity;
import nl.cherement.jak.repository.BoardRepository;
import nl.cherement.jak.repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BoardService extends AbstractService<BoardEntity> {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    UserRespository userRespository;

    public BoardService(BoardRepository repository) {
        super(repository);
    }

    public List<BoardEntity> findByUserName(String username) {
        return boardRepository.findByUsers_Username(username);
    }

    public void addUser(Long boardId, Long userId) {
        BoardEntity board = boardRepository.getOne(boardId);
        UserEntity user = userRespository.getOne(userId);

        List<UserEntity> boardUsers = board.getUsers();
        boardUsers.add(user);
        board.setUsers(boardUsers);

        boardRepository.save(board);
    }

}