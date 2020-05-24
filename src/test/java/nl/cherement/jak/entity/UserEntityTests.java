package nl.cherement.jak.entity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
class UserEntityTests {

    private final UserEntity userEntity = new UserEntity();

    @Test
    void id() {
        userEntity.setId(1);
        assertEquals(1, userEntity.getId());
    }

    @Test
    void userName() {
        userEntity.setUsername("UserTest");
        assertEquals("UserTest", userEntity.getUsername());
    }

    @Test
    void password() {
        userEntity.setPassword("PasswordTest");
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        assertNotEquals(bCryptPasswordEncoder.encode(bCryptPasswordEncoder.encode("PasswordTest")), userEntity.getPassword());
    }

    @Test
    void roles() {
        userEntity.setRoles("GUEST,ADMIN");
        assertEquals("GUEST,ADMIN", userEntity.getRoles());
    }

    @Test
    void roleList() {
        userEntity.setRoles("GUEST,ADMIN");
        assertEquals(Arrays.asList("GUEST", "ADMIN"), userEntity.getRoleList());
    }

    @Test
    void permissions() {
        userEntity.setPermissions("READ,WRITE");
        assertEquals("READ,WRITE", userEntity.getPermissions());
    }

    @Test
    void permissionList() {
        userEntity.setPermissions("READ,WRITE");
        assertEquals(Arrays.asList("READ", "WRITE"), userEntity.getPermissionList());
    }

    @Test
    void active() {
        userEntity.setActive(1);
        assertEquals(1, userEntity.getActive());
    }
}
