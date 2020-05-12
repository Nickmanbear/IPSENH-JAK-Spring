package nl.cherement.jak.service;

import nl.cherement.jak.entity.BoardEntity;
import nl.cherement.jak.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {
     
    @Autowired
    BoardRepository repository;
     
    public List<BoardEntity> all() {
        return repository.findAll();
    }
     
    public Optional<BoardEntity> single(Long id) {
        return repository.findById(id);
    }
     
    public BoardEntity update(BoardEntity boardEntity) {
        return repository.save(boardEntity);
    }
     
    public void remove(Long id) {
        repository.deleteById(id);
    }
}