package nl.cherement.jak.repository;

import nl.cherement.jak.entity.ColumnEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColumnRepository
        extends JpaRepository<ColumnEntity, Long> {

    @Query(value = "SELECT c FROM ColumnEntity c WHERE c.boardId = :id")
    List<ColumnEntity> getByBoardId(@Param("id") Long id);
}
