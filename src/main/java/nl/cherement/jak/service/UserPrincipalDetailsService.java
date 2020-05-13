package nl.cherement.jak.service;

import nl.cherement.jak.entity.UserEntity;
import nl.cherement.jak.model.UserPrinicipal;
import nl.cherement.jak.repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {

    @Autowired
    private final UserRespository userRespository;

    public UserPrincipalDetailsService(UserRespository userRespository) {
        this.userRespository = userRespository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserEntity user = this.userRespository.findByUsername(username);
        return new UserPrinicipal(user);

    }


    public UserDetails auth(Authentication authentication) {
        return (UserDetails) authentication.getPrincipal();
    }


    public List<UserEntity> findAll() {
        return this.userRespository.findAll();
    }

    public Optional<UserEntity> get(Long id) {
        return this.userRespository.findById(id);
    }

    public UserEntity add(UserEntity user) {
        return this.userRespository.save(user);
    }
}
