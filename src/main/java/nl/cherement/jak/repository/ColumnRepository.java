package nl.cherement.jak.repository;

import nl.cherement.jak.entity.ColumnEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColumnRepository extends JpaRepository<ColumnEntity, Long> {
    List<ColumnEntity> findByBoardId(Long boardId);
    @java.lang.SuppressWarnings("java:S100")
    List<ColumnEntity> findTopByBoard_IdOrderByIdDesc(Long boardId);

}
