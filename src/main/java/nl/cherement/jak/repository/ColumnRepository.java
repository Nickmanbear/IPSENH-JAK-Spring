package nl.cherement.jak.repository;

import nl.cherement.jak.entity.ColumnEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColumnRepository
        extends JpaRepository<ColumnEntity, Long> {
 
}
