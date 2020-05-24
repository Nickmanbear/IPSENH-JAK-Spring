package nl.cherement.jak.service;

import nl.cherement.jak.entity.BoardEntity;
import nl.cherement.jak.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BoardService extends AbstractService<BoardEntity> {

    @Autowired
    BoardRepository repository;

    public BoardService(BoardRepository repository) {
        super(repository);
    }

    public List<BoardEntity> findByUserName(String username) {
        return repository.findByUsers_Username(username);
    }

}