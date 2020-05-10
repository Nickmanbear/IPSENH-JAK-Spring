package com.example.jakban.controller;

import com.example.jakban.model.User;
import com.example.jakban.repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/public")
public class UserController {


    private UserRespository userRepository;

    public UserController(UserRespository userService) {
        this.userRepository = userService;
    }

//    @RequestMapping("/login")
//    public boolean login(@RequestBody SecurityProperties.User user) {
//        return
//
//                user.getName().equals(userRepository.loadUserByUsername(user.getName()).getUsername());
//    }

    @GetMapping("users")
    public List<User> user(HttpServletRequest request) {
        return userRepository.findAll();

    }
}