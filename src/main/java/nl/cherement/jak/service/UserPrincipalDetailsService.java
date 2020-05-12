package nl.cherement.jak.service;

import nl.cherement.jak.entity.UserEntity;
import nl.cherement.jak.model.UserPrinicipal;
import nl.cherement.jak.repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {

    @Autowired
    private UserRespository userRespository;

    public UserPrincipalDetailsService(UserRespository userRespository) {
        this.userRespository = userRespository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = this.userRespository.findByUsername(username);
        UserPrinicipal userPrinicipal = new UserPrinicipal(user);
        return userPrinicipal;
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

    public void add(UserEntity user) {
        this.userRespository.save(user);
    }
}
