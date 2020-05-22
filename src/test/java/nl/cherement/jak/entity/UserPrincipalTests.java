package nl.cherement.jak.entity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserPrincipalTests {

    private final UserPrincipal userPrincipal = new UserPrincipal(new UserEntity());

    @Test
    void getAuthorities() {
        assertEquals(new ArrayList<>(), userPrincipal.getAuthorities());
    }
}
