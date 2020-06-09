package nl.cherement.jak.service;

import nl.cherement.jak.Application;
import nl.cherement.jak.entity.UserEntity;
import nl.cherement.jak.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
class UserDetailsServiceImplTests {

    private UserEntity user;

    @Autowired
    private UserDetailsServiceImpl service;

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

        doReturn(user).when(repository).findByUsername(any());
    }

    @Test
    void loadUserByUsername() {
        assertEquals(user.username, service.loadUserByUsername("admin").getUsername());

    }
}
