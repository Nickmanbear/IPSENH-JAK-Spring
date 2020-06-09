package nl.cherement.jak.controller;

import nl.cherement.jak.entity.UserEntity;
import nl.cherement.jak.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public Map<Long, String> findAllShortened() {
        return userService.findAllShortened();
    }

    @GetMapping("/me")
    public UserEntity getUser(Authentication authentication) {

        return userService.findByUsername(authentication.getName());
    }

    @PostMapping("/register")
    public UserEntity signUp(@RequestBody UserDTO userDTO) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        userDTO.password = bCryptPasswordEncoder.encode(userDTO.password);

        return userService.save(userDTO.toEntity());
    }
}

class UserDTO extends UserEntity {

    UserEntity toEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.id = id;
        userEntity.username = username;
        userEntity.password = password;
        userEntity.active = active;
        userEntity.permissions = permissions;
        userEntity.roles = roles;

        return userEntity;
    }
}