package nl.cherement.jak.service;

import nl.cherement.jak.entity.ColumnEntity;
import nl.cherement.jak.repository.ColumnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ColumnService extends AbstractService<ColumnEntity> {

    @Autowired
    ColumnRepository repository;

    public ColumnService(ColumnRepository repository) {
        super(repository);
    }
}