package nl.cherement.jak.repository;

import nl.cherement.jak.entity.BoardEntity;
import nl.cherement.jak.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long> {
    @java.lang.SuppressWarnings("java:S100")
    List<EventEntity> findByTo_Board(BoardEntity board);
}
