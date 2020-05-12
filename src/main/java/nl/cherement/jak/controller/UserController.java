package nl.cherement.jak.controller;

import nl.cherement.jak.model.User;
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

    private UserRespository userRepository;

    public UserController(UserRespository userService) {
        this.userRepository = userService;
    }


    @GetMapping("/all")
    public List<User> user(HttpServletRequest request) {
        return userPrincipalDetailsService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> get(HttpServletRequest request, @PathVariable("id") Long id) {
        return userPrincipalDetailsService.get(id);
    }
    @GetMapping("/me")
    public UserDetails auth(Authentication authentication) {
        return userPrincipalDetailsService.auth(authentication);
    }

    @PostMapping
    public ResponseEntity newUser(@RequestBody User user) {
        userPrincipalDetailsService.add(user);
        return ResponseEntity.ok().build();
    }
}