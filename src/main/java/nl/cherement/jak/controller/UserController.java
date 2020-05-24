package nl.cherement.jak.controller;

import nl.cherement.jak.entity.UserEntity;
import nl.cherement.jak.model.UserModel;
import nl.cherement.jak.service.UserPrincipalDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;



    @GetMapping
    public List<UserEntity> findAll(HttpServletRequest request) {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<UserEntity> find(HttpServletRequest request, @PathVariable("id") Long id) {
        return userService.findById(id);
    }

    @GetMapping("/me")
    public UserEntity getUser(Authentication authentication) {

        return userService.findByUsername((String) authentication.getPrincipal());
    }


    @PostMapping("/register")
    public UserEntity signUp(@RequestBody UserDTO userDTO) {

        return userService.save(userDTO.toEntity());

    }
}
class UserDTO extends UserEntity {

    @Override
    public void setPassword(String password) {
        this.password = bCryptPasswordEncoder.encode(password);
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