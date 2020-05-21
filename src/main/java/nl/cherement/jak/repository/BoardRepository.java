package nl.cherement.jak.repository;

import nl.cherement.jak.entity.BoardEntity;
import nl.cherement.jak.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository
        extends JpaRepository<BoardEntity, Long> {
    List<BoardEntity> findByUsers(UserEntity user);
}
