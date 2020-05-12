package nl.cherement.jak.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class UserModel {

    public long id;
    public String username;
    public String password;
    public int active;
    public int status;
    public String roles = "";
    public String permissions = "";

    public UserModel(String username, String password, String roles, String permissions, int active, int status) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.permissions = permissions;
        this.active = active;
        this.status = status;
    }


}

