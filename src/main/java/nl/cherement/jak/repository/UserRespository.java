package nl.cherement.jak.repository;

import nl.cherement.jak.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRespository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);

}
