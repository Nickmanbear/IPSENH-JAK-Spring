package nl.cherement.jak.service;

import nl.cherement.jak.entity.UserEntity;
import nl.cherement.jak.model.UserPrinicipal;
import nl.cherement.jak.repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserPrincipalDetailsService extends AbstractService<UserEntity> implements UserDetailsService {

    @Autowired
    UserRespository repository;

    public UserPrincipalDetailsService(UserRespository repository) {
        super(repository);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserEntity user = this.repository.findByUsername(username);
        return new UserPrinicipal(user);

    }

    public UserEntity auth(Authentication authentication) {
        return repository.findByUsername(authentication.getName());
    }
}
