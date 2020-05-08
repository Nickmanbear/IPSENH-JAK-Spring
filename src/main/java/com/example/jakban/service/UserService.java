package com.example.jakban.service;

import com.example.jakban.model.User;
import com.example.jakban.repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRespository userRepository;

    @Autowired
    public UserService(UserRespository userRepository) {
        this.userRepository = userRepository;
    }

//    public List<User> findAll(){
//        Iterable<User> it = userRespository.findAll();
//        return (List<User>) it;
//    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }
}
