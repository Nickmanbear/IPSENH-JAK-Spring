package com.example.jakban.service;

import com.example.jakban.model.User;
import com.example.jakban.model.UserPrinicipal;
import com.example.jakban.repository.UserRespository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {

    private UserRespository userRespository;

    public UserPrincipalDetailsService(UserRespository userRespository){
        this.userRespository= userRespository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRespository.findByUsername(username);
        UserPrinicipal userPrinicipal= new UserPrinicipal(user);
        return  userPrinicipal;
    }
}
