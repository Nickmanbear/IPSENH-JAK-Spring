package nl.cherement.jak.controller;

import nl.cherement.jak.entity.UserEntity;
import nl.cherement.jak.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;


@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @GetMapping
    public HashMap<Long, String> findAllShortened() {
        return userService.findAllShortened();
    }

    @GetMapping("/me")
    public UserEntity findUser(Principal principal) {

        return userService.findByUsername(principal.getName());
    }


    @PostMapping("/register")
    public UserEntity signUp(@RequestBody UserDTO userDTO) {

        return userService.save(userDTO.toEntity());

    }
}
class UserDTO extends UserEntity {

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

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