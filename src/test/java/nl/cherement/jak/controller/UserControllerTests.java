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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
class UserControllerTests {

    private final UserDTO userDTO = new UserDTO();

    private UserEntity userEntity = new UserEntity();

    @Autowired
    private UserController controller;

    @MockBean
    private UserService service;

    @MockBean
    private Principal principal;

    @BeforeEach
    public void initialize(){

        userEntity.setId(1);
        userEntity.setActive(1);
        userEntity.setPassword("password");
        userEntity.setPermissions("admin");
        userEntity.setRoles("ROLE_ADMIN");

        userDTO.setActive(1);
        userDTO.setId(1);
        userDTO.setPassword("TestPassword");
        userDTO.setPermissions("READ,WRITE");
        userDTO.setRoles("GUEST,ADMIN");
        userDTO.setUsername("TestUser");


        doReturn("admin").when(principal).getName();

        doReturn(userEntity).when(service).findByUsername(any());

        doReturn(userDTO.toEntity()).when(service).save(any(UserEntity.class));

    }


    @Test
    void DTOPassword() {
        userDTO.setPassword("TestPassword");
        assertEquals("TestPassword", userDTO.getPassword());
    }

    @Test
    void DTO() {
        userDTO.setActive(1);
        userDTO.setId(1);
        userDTO.setPassword("TestPassword");
        userDTO.setPermissions("READ,WRITE");
        userDTO.setRoles("GUEST,ADMIN");
        userDTO.setUsername("TestUser");

        UserEntity userEntity = userDTO.toEntity();
        assertEquals(1, userEntity.getActive());
        assertEquals(1, userEntity.getId());
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        assertNotEquals(bCryptPasswordEncoder.encode(bCryptPasswordEncoder.encode("TestPassword")), userEntity.getPassword());
        assertEquals("GUEST,ADMIN", userEntity.getRoles());
        assertEquals(Arrays.asList("GUEST", "ADMIN"), userEntity.getRoleList());
        assertEquals("READ,WRITE", userEntity.getPermissions());
        assertEquals(Arrays.asList("READ", "WRITE"), userEntity.getPermissionList());
        assertEquals("TestUser", userEntity.getUsername());
    }

    @Test
    void getUser() {

        assertSame(userEntity, controller.getUser(principal));


    }

    @Test
    void signUp() {

        assertEquals(userDTO.toEntity().getId(), controller.signUp(userDTO).getId());
        assertEquals(userDTO.toEntity().getActive(), controller.signUp(userDTO).getActive());
        assertEquals(userDTO.toEntity().getPermissionList(), controller.signUp(userDTO).getPermissionList());
        assertEquals(userDTO.toEntity().getPermissions(), controller.signUp(userDTO).getPermissions());
        assertEquals(userDTO.toEntity().getRoleList(), controller.signUp(userDTO).getRoleList());
        assertEquals(userDTO.toEntity().getUsername(), controller.signUp(userDTO).getUsername());
        assertNotEquals(userDTO.toEntity().getPassword(), controller.signUp(userDTO).getPassword());

    }
}
