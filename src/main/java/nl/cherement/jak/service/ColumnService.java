package nl.cherement.jak.service;

import nl.cherement.jak.entity.ColumnEntity;
import nl.cherement.jak.repository.ColumnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColumnService extends ServiceAbstract<ColumnEntity>{

    @Autowired
    ColumnRepository repository;

    public ColumnService(ColumnRepository repository) {
        super(repository);
    }

    public List<ColumnEntity> getByBoardId(Long id) {
        return this.repository.findAll();
    }

}