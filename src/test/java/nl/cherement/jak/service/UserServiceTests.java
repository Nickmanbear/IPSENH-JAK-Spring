package nl.cherement.jak.service;

import nl.cherement.jak.entity.UserEntity;
import nl.cherement.jak.repository.UserRespository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
class UserServiceTests {

    private UserEntity user;

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

        doReturn(user).when(repository).findByUsername(any());
    }

    @Test
    void findByUsername() {
        assertSame(user.getUsername(), service.findByUsername("admin").getUsername());
    }
}