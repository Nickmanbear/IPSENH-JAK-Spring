package nl.cherement.jak.service;

import nl.cherement.jak.entity.ColumnEntity;
import nl.cherement.jak.exception.RecordNotFoundException;
import nl.cherement.jak.model.ColumnModel;
import nl.cherement.jak.repository.ColumnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ColumnService {
     
    @Autowired
    ColumnRepository repository;
     
    public List<ColumnEntity> all() {
        List<ColumnEntity> columnEntities = repository.findAll();
         
        if(!columnEntities.isEmpty()) {
            return columnEntities;
        } else {
            return new ArrayList<>();
        }
    }
     
    public ColumnEntity single(Long id) throws RecordNotFoundException {
        return repository.findById(id)
                .orElseThrow(RecordNotFoundException::new);
    }
     
    public ColumnEntity update(ColumnModel columnModel) throws RecordNotFoundException {
        ColumnEntity columnEntity = new ColumnEntity();
        columnEntity.importModal(columnModel);

        if (columnEntity.getId() > 0) {
            if (repository.findById(columnModel.getId()).isPresent()) {
                return repository.save(columnEntity);
            } else {
                throw new RecordNotFoundException();
            }
        } else {
            return repository.save(columnEntity);
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