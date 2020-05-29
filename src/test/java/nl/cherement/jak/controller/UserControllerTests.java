package nl.cherement.jak.controller;

import nl.cherement.jak.entity.UserEntity;
import nl.cherement.jak.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.Principal;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
class UserControllerTests {

    private final UserDTO userDTO = new UserDTO();

    private final UserEntity userEntity = new UserEntity();

    private final HashMap<Long, String> shortenedUsers = new HashMap<>();

    @Autowired
    private UserController controller;

    @MockBean
    private UserService service;

    @MockBean
    private Principal principal;

    @BeforeEach
    public void initialize(){
        userEntity.id = 1;
        userEntity.active = true;
        userEntity.password = "password";
        userEntity.permissions = "admin";
        userEntity.roles = "ROLE_ADMIN";

        userDTO.active = true;
        userDTO.id = 1;
        userDTO.password = "TestPassword";
        userDTO.permissions = "READ,WRITE";
        userDTO.roles = "GUEST,ADMIN";
        userDTO.username = "TestUser";

        shortenedUsers.put(1L, "username1");
        shortenedUsers.put(2L, "username2");
        shortenedUsers.put(3L, "username3");

        doReturn("admin").when(principal).getName();
        doReturn(shortenedUsers).when(service).findAllShortened();
        doReturn(userEntity).when(service).findByUsername(any());
        doReturn(userDTO.toEntity()).when(service).save(any(UserEntity.class));
    }

    @Test
    void DTO() {
        userDTO.id = (1);
        userDTO.username = ("TestUser");
        userDTO.password = ("TestPassword");
        userDTO.permissions = ("READ,WRITE");
        userDTO.roles = ("GUEST,ADMIN");
        userDTO.active = true;
        UserEntity userEntity = userDTO.toEntity();

        assertEquals(1, userEntity.id);
        assertEquals("TestUser", userEntity.username);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        assertNotEquals(bCryptPasswordEncoder.encode(bCryptPasswordEncoder.encode("TestPassword")), userEntity.password);
        assertEquals("GUEST,ADMIN", userEntity.roles);
        assertEquals(Arrays.asList("GUEST", "ADMIN"), userEntity.getRoleList());
        assertEquals("READ,WRITE", userEntity.permissions);
        assertEquals(Arrays.asList("READ", "WRITE"), userEntity.getPermissionList());
        assertTrue(userEntity.active);
    }

    @Test
    void findAllShortened() {
        assertSame(shortenedUsers, controller.findAllShortened());
    }

    @Test
    void signUp() {
        assertEquals(userDTO.toEntity().id, controller.signUp(userDTO).id);
        assertEquals(userDTO.toEntity().username, controller.signUp(userDTO).username);
        assertNotEquals(userDTO.toEntity().password, controller.signUp(userDTO).password);
        assertEquals(userDTO.toEntity().getPermissionList(), controller.signUp(userDTO).getPermissionList());
        assertEquals(userDTO.toEntity().permissions, controller.signUp(userDTO).permissions);
        assertEquals(userDTO.toEntity().getRoleList(), controller.signUp(userDTO).getRoleList());
        assertEquals(userDTO.toEntity().roles, controller.signUp(userDTO).roles);
        assertEquals(userDTO.toEntity().active, controller.signUp(userDTO).active);
    }
}
