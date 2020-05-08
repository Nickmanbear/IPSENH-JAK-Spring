package nl.cherement.jak.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nl.cherement.jak.model.Card;
 
@Repository
public interface CardRepository
        extends JpaRepository<Card, Long> {
 
}
