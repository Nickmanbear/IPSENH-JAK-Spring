package nl.cherement.jak.service;

import nl.cherement.jak.entity.CardEntity;
import nl.cherement.jak.entity.ColumnEntity;
import nl.cherement.jak.exception.RecordNotFoundException;
import nl.cherement.jak.model.ColumnModel;
import nl.cherement.jak.repository.CardRepository;
import nl.cherement.jak.repository.ColumnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ColumnService {

    @Autowired
    ColumnRepository repository;

    public List<ColumnEntity> all() {
        return repository.findAll();
    }

    public Optional<ColumnEntity> single(Long id) {
        return repository.findById(id);
    }

    public ColumnEntity update(ColumnEntity columnEntity) {
        return repository.save(columnEntity);
    }

    public void remove(Long id) {
        repository.deleteById(id);
    }
}