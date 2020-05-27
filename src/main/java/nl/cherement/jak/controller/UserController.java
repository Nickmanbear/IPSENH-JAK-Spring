package nl.cherement.jak.controller;

import nl.cherement.jak.entity.UserEntity;
import nl.cherement.jak.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Hashtable;


@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @GetMapping
    public Hashtable<Long, String> findAllShortened() {
        return userService.findAllShortened();
    }

    @GetMapping("/me")
    public UserEntity findUser(Principal principal) {

        return userService.findByUsername(principal.getName());
    }


    @PostMapping("/register")
    public UserEntity signUp(@RequestBody UserDTO userDTO) {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));

        return userService.save(userDTO.toEntity());

    }
}
class UserDTO extends UserEntity {

    UserEntity toEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setActive(getActive());
        userEntity.setId(getId());
        userEntity.setPassword(getPassword());
        userEntity.setPermissions(getPermissions());
        userEntity.setRoles(getRoles());
        userEntity.setUsername(getUsername());

        return userEntity;
    }
}