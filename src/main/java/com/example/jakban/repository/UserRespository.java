package com.example.jakban.repository;

import com.example.jakban.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface UserRespository extends JpaRepository<User, Long> {
    User findByUsername(String username);

}
