package nl.cherement.jak.service;

import nl.cherement.jak.entity.UserEntity;
import nl.cherement.jak.repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Hashtable;
import java.util.List;

@Service
public class UserService  extends AbstractService<UserEntity>{

    @Autowired
    UserRespository repository;

    public UserService(UserRespository repository) {
        super(repository);
    }

    public Hashtable<Long, String> findAllShortened() {
        Hashtable<Long, String> shortenedUsers = new Hashtable<>();
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
