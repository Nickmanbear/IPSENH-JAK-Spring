package nl.cherement.jak.controller;

import nl.cherement.jak.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
class UserControllerTests {

    private final UserDTO userDTO = new UserDTO();

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
}
