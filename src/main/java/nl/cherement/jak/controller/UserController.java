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

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserPrincipalDetailsService userPrincipalDetailsService;


    @GetMapping
    public List<UserEntity> findAll(HttpServletRequest request) {
        return userPrincipalDetailsService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<UserEntity> find(HttpServletRequest request, @PathVariable("id") Long id) {
        return userPrincipalDetailsService.findById(id);
    }

    @GetMapping("/me")
    public UserEntity auth(Authentication authentication) {
        return userPrincipalDetailsService.auth(authentication);
    }

    @PostMapping
    public UserEntity save(@RequestBody UserModel userModel) {
        UserEntity userEntity = new UserEntity();
        userEntity.setActive(userModel.getActive());
        userEntity.setId(userModel.getId());
        userEntity.setPassword(userModel.getPassword());
        userEntity.setPermissions(userModel.getPermissions());
        userEntity.setRoles(userModel.getRoles());
        userEntity.setUsername(userModel.getUsername());
        return userPrincipalDetailsService.save(userEntity);
    }
}