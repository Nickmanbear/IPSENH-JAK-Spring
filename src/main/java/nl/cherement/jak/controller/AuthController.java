package nl.cherement.jak.controller;

import nl.cherement.jak.model.UserModel;
import nl.cherement.jak.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;


    @PostMapping(
            path = "/login",
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public UserDetails login(UserModel userModel) throws ChangeSetPersister.NotFoundException {
        UserDetails auth = userDetailsServiceImpl.loadUserByUsername(userModel.getUsername());
        if (auth.getAuthorities().isEmpty()) {
            throw new ChangeSetPersister.NotFoundException();

        }
        if (BCrypt.checkpw(userModel.getPassword(), auth.getPassword())) {
            return auth;
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }

    }
}
