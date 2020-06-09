package nl.cherement.jak.service;

import nl.cherement.jak.Application;
import nl.cherement.jak.entity.UserEntity;
import nl.cherement.jak.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
class UserServiceTests {

    private UserEntity user;

    @Autowired
    private UserService service;

    @MockBean
    private UserRepository repository;

    @BeforeEach
    public void initialize() {
        user = new UserEntity();
        user.id = 1L;
        user.active = true;
        user.username = "admin";
        user.password = "password";
        user.permissions = "admin";
        user.roles = "ADMIN";
        List<UserEntity> userList = new ArrayList<>();
        userList.add(user);

        doReturn(user).when(repository).findByUsername(any());
        doReturn(userList).when(repository).findAll();
    }

    @Test
    void findByUsername() {
        assertSame(user.username, service.findByUsername("admin").username);
    }

    @Test
    void findAllShortened() {
        Map<Long, String> shortenedUser = new HashMap<>();
        shortenedUser.put(user.id, user.username);

        assertSame(shortenedUser.get(1L), service.findAllShortened().get(1L));
    }
}
