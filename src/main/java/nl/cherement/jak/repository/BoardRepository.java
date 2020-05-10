package nl.cherement.jak.repository;

import nl.cherement.jak.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository
        extends JpaRepository<BoardEntity, Long> {
 
}
