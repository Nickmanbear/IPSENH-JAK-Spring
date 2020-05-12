package nl.cherement.jak.controller;

import nl.cherement.jak.entity.UserEntity;
import nl.cherement.jak.model.UserModel;
import nl.cherement.jak.repository.UserRespository;
import nl.cherement.jak.service.UserPrincipalDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserPrincipalDetailsService userPrincipalDetailsService;


    @GetMapping("/all")
    public List<UserEntity> user(HttpServletRequest request) {
        return userPrincipalDetailsService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<UserEntity> get(HttpServletRequest request, @PathVariable("id") Long id) {
        return userPrincipalDetailsService.get(id);
    }
    @GetMapping("/me")
    public UserDetails auth(Authentication authentication) {
        return userPrincipalDetailsService.auth(authentication);
    }

    @PostMapping
    public ResponseEntity newUser(@RequestBody UserModel userModel) {

        userPrincipalDetailsService.add(new UserEntity(userModel.username,userModel.password,userModel.roles,
                userModel.permissions, userModel.active));
        return ResponseEntity.ok().build();
    }
}