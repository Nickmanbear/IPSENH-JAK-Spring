package nl.cherement.jak.repository;

import nl.cherement.jak.entity.TeamEntity;
import nl.cherement.jak.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<TeamEntity, Long> {
    List<TeamEntity> findByMembers(UserEntity member);
}
