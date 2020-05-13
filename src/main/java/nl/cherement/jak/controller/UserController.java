package nl.cherement.jak.controller;

import nl.cherement.jak.entity.UserEntity;
import nl.cherement.jak.model.UserModel;
import nl.cherement.jak.service.UserPrincipalDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public UserEntity newUser(@RequestBody UserModel userModel) {
        UserEntity userEntity = new UserEntity();
        userEntity.setActive(userModel.getActive());
        userEntity.setId(userModel.getId());
        userEntity.setPassword(userModel.getPassword());
        userEntity.setPermissions(userModel.getPermissions());
        userEntity.setRoles(userModel.getRoles());
        userEntity.setUsername(userModel.getUsername());
        return userPrincipalDetailsService.add(userEntity);
    }


}