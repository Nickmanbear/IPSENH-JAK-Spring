package nl.cherement.jak.service;

import nl.cherement.jak.entity.UserEntity;
import nl.cherement.jak.repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService  extends AbstractService<UserEntity>{

    @Autowired
    UserRespository repository;

    public UserService(UserRespository repository) {
        super(repository);
    }

    public UserEntity findByUsername(String username){
        return this.repository.findByUsername(username);
    }
}
