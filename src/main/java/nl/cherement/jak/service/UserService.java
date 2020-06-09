package nl.cherement.jak.service;

import nl.cherement.jak.entity.UserEntity;
import nl.cherement.jak.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService  extends AbstractService<UserEntity>{

    @Autowired
    UserRepository repository;

    public UserService(UserRepository repository) {
        super(repository);
    }

    public Map<Long, String> findAllShortened() {
        HashMap<Long, String> shortenedUsers = new HashMap<>();
        List<UserEntity> userEntities = repository.findAll();

        for (UserEntity user : userEntities) {
            shortenedUsers.put(user.id, user.username);
        }

        return shortenedUsers;
    }

    public UserEntity findByUsername(String username){
        return this.repository.findByUsername(username);
    }

    public UserEntity save(UserEntity userEntity) {
        if (userEntity.id != 0L) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You can not edit existing users");
        }

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        userEntity.password = bCryptPasswordEncoder.encode(userEntity.password);

        return repository.save(userEntity);
    }

    @Override
    boolean hasAccess(Authentication authentication, UserEntity entity) {
        return true;
    }
}
