package nl.cherement.jak.service;

import nl.cherement.jak.entity.UserEntity;
import nl.cherement.jak.repository.UserRespository;
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
    private List<UserEntity> userList;

    @Autowired
    private UserService service;

    @MockBean
    private UserRespository repository;

    @BeforeEach
    public void initialize() {
        user = new UserEntity();
        user.setId(1);
        user.setActive(1);
        user.setUsername("admin");
        user.setPassword("password");
        user.setPermissions("admin");
        user.setRoles("ROLE_ADMIN");

        userList = new ArrayList<>();
        userList.add(user);

        doReturn(user).when(repository).findByUsername(any());

        doReturn(userList).when(repository).findAll();
    }

    @Test
    void findByUsername() {
        assertSame(user.getUsername(), service.findByUsername("admin").getUsername());
    }

    @Test
    void findAllShortened() {

        Map<Long, String> shortenedUser = new HashMap<>();
        shortenedUser.put(user.getId(), user.getUsername());

        assertSame(shortenedUser.get(1L), service.findAllShortened().get(1L));
    }
}
