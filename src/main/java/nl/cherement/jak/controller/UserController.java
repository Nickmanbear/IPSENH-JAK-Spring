package nl.cherement.jak.controller;

import nl.cherement.jak.entity.UserEntity;
import nl.cherement.jak.model.UserModel;
import nl.cherement.jak.repository.UserRespository;
import nl.cherement.jak.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserRespository userRespository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;



    @GetMapping
    public List<UserEntity> findAll(HttpServletRequest request) {
        return userRespository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<UserEntity> find(HttpServletRequest request, @PathVariable("id") Long id) {
        return userRespository.findById(id);
    }


    @PostMapping("/register")
    public void signUp(@RequestBody UserEntity user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        this.userRespository.save(user);
    }
}