package nl.cherement.jak.service;

import nl.cherement.jak.entity.BoardEntity;
import nl.cherement.jak.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class BoardService extends AbstractService<BoardEntity> {
     
    @Autowired
    BoardRepository repository;

    public BoardService(BoardRepository repository) {
        super(repository);
    }
}