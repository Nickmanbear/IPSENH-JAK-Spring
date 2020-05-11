package com.example.jakban.service;

import com.example.jakban.model.User;
import com.example.jakban.model.UserPrinicipal;
import com.example.jakban.repository.UserRespository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {

    private UserRespository userRespository;

    public UserPrincipalDetailsService(UserRespository userRespository) {
        this.userRespository = userRespository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRespository.findByUsername(username);
        UserPrinicipal userPrinicipal = new UserPrinicipal(user);
        return userPrinicipal;
    }


    public UserDetails auth(Authentication authentication) {
        return (UserDetails) authentication.getPrincipal();
    }


    public List<User> findAll() {
        return this.userRespository.findAll();
    }

    public Optional<User> get(Long id) {
        return this.userRespository.findById(id);
    }

    public void add(User user) {
        this.userRespository.save(user);
    }
}
