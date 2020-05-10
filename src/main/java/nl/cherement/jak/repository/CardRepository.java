package nl.cherement.jak.repository;

import nl.cherement.jak.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
@Repository
public interface CardRepository
        extends JpaRepository<CardEntity, Long> {
 
}
