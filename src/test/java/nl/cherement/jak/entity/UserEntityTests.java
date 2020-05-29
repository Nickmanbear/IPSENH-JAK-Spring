package nl.cherement.jak.entity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserEntityTests {

    private final UserEntity userEntity = new UserEntity();

    @Test
    void roleList() {
        userEntity.roles = ("GUEST,ADMIN");
        assertEquals(Arrays.asList("GUEST", "ADMIN"), userEntity.getRoleList());
    }

    @Test
    void permissionList() {
        userEntity.permissions = ("READ,WRITE");
        assertEquals(Arrays.asList("READ", "WRITE"), userEntity.getPermissionList());
    }
}
