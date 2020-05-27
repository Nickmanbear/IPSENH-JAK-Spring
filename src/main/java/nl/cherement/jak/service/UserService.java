package nl.cherement.jak.service;

import nl.cherement.jak.entity.UserEntity;
import nl.cherement.jak.repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService  extends AbstractService<UserEntity>{

    @Autowired
    UserRespository repository;

    public UserService(UserRespository repository) {
        super(repository);
    }

    public Map<Long, String> findAllShortened() {
        HashMap<Long, String> shortenedUsers = new HashMap<>();
        List<UserEntity> userEntities = repository.findAll();

        for (UserEntity user : userEntities) {
            shortenedUsers.put(user.getId(), user.getUsername());
        }
        return shortenedUsers;
    }

    public UserEntity findByUsername(String username){
        return this.repository.findByUsername(username);
    }
}
