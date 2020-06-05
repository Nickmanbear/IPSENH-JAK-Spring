package nl.cherement.jak.service;

import nl.cherement.jak.entity.ColumnEntity;
import nl.cherement.jak.repository.ColumnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColumnService extends AbstractService<ColumnEntity> {

    @Autowired
    ColumnRepository repository;

    public ColumnService(ColumnRepository repository) {
        super(repository);
    }

    public List<ColumnEntity> getByBoardId(Long id) {
        return this.repository.findByBoardId(id);
    }

    @Override
    boolean hasAccess(Authentication authentication, ColumnEntity entity) {
        return true;
    }
}