package nl.cherement.jak.controller;

import nl.cherement.jak.entity.UserEntity;
import nl.cherement.jak.service.UserPrincipalDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
    public UserEntity save(@RequestBody UserDTO userDTO) {
        return userPrincipalDetailsService.save(userDTO.toEntity());
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