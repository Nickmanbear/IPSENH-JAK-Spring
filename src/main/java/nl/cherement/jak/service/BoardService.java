package nl.cherement.jak.service;

import nl.cherement.jak.entity.BoardEntity;
import nl.cherement.jak.exception.RecordNotFoundException;
import nl.cherement.jak.model.BoardModel;
import nl.cherement.jak.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {
     
    @Autowired
    BoardRepository repository;
     
    public List<BoardEntity> all() {
        List<BoardEntity> boardEntities = repository.findAll();
         
        if(!boardEntities.isEmpty()) {
            return boardEntities;
        } else {
            return new ArrayList<>();
        }
    }
     
    public BoardEntity single(Long id) throws RecordNotFoundException {
        return repository.findById(id)
                .orElseThrow(RecordNotFoundException::new);
    }
     
    public BoardEntity update(BoardModel boardModel) throws RecordNotFoundException {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.importModal(boardModel);

        if (boardEntity.getId() > 0) {
            if (repository.findById(boardModel.getId()).isPresent()) {
                return repository.save(boardEntity);
            } else {
                throw new RecordNotFoundException();
            }
        } else {
            return repository.save(boardEntity);
        }
    }
     
    public void remove(Long id) throws RecordNotFoundException {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException();
        }
    }
}