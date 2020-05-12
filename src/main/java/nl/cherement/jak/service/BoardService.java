package nl.cherement.jak.service;

import nl.cherement.jak.entity.BoardEntity;
import nl.cherement.jak.repository.BoardRepository;
import nl.cherement.jak.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class BoardService extends ServiceAbstract<BoardEntity>{
     
    @Autowired
    BoardRepository repository;

    public BoardService(CardRepository repository) {
        super(repository);
    }
}