package nl.cherement.jak.service;

import nl.cherement.jak.entity.UserEntity;
import nl.cherement.jak.repository.UserRespository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRespository repository;

    public UserDetailsServiceImpl(UserRespository repository) {

        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserEntity user = repository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(user.getUsername(), user.getPassword(), emptyList());

    }

}
