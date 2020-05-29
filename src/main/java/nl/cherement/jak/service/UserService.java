package nl.cherement.jak.service;

import nl.cherement.jak.entity.UserEntity;
import nl.cherement.jak.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
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

    @Override
    boolean hasAccess(Principal user, UserEntity obj) {
        return true;
    }
}
