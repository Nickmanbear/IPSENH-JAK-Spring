package nl.cherement.jak.controller;

import nl.cherement.jak.entity.UserEntity;
import nl.cherement.jak.model.UserModel;
import nl.cherement.jak.service.UserDetailsServiceImpl;
import nl.cherement.jak.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    public void signUp(@RequestBody UserModel userModel) {
        UserEntity user = new UserEntity();
        user.importModal(userModel);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        this.userService.save(user);
    }
}
