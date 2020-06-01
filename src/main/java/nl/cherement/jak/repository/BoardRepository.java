package nl.cherement.jak.repository;

import nl.cherement.jak.entity.BoardEntity;
import nl.cherement.jak.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    @java.lang.SuppressWarnings("java:S100")
    List<BoardEntity> findByUsers_Username(String username);

    List<BoardEntity> findByTeam(TeamEntity team);
}
