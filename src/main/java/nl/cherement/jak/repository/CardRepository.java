package nl.cherement.jak.repository;

import nl.cherement.jak.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository
        extends JpaRepository<CardEntity, Long> {


    @Query(value = "SELECT c FROM CardEntity c WHERE c.columnId = :id")
    List<CardEntity> getByColumnId(@Param("id") Long id);
}
