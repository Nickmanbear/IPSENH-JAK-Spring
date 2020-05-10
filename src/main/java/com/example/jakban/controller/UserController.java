package com.example.jakban.controller;

import com.example.jakban.model.User;
import com.example.jakban.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Base64;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/login")
    public boolean login(@RequestBody SecurityProperties.User user) {
        return

                user.getName().equals(userService.loadUserByUsername(user.getName()).getUsername());
    }

    @GetMapping("/user")
    public UserDetails user(HttpServletRequest request) {
        return  userService.loadUserByUsername("user");

    }
}