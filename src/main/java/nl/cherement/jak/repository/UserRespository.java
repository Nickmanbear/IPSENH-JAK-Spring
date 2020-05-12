package nl.cherement.jak.repository;

import nl.cherement.jak.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRespository extends JpaRepository<User, Long> {
    User findByUsername(String username);

}
