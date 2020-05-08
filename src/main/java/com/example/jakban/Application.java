package com.example.jakban;

import com.example.jakban.model.User;
import com.example.jakban.repository.UserRespository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(UserRespository repository) {
        return (args) -> {
            // save a few customers
            User user = new User();
            user.setUsername("user4");
            BCryptPasswordEncoder bCryptPasswordEncoder =new BCryptPasswordEncoder();
            user.setPassword(bCryptPasswordEncoder.encode("password"));
            repository.save(user);

        };
    }
}