package nl.cherement.jak.entity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserPrincipalTests {

    private UserPrincipal userPrincipal = new UserPrincipal(new UserEntity());

    @Test
    void getAuthoritiesEmpty() {
        UserEntity userEntity = new UserEntity();
        userPrincipal = new UserPrincipal(userEntity);

        assertEquals(new ArrayList<>(), userPrincipal.getAuthorities());
    }

    @Test
    void getAuthoritiesPermissions() {
        UserEntity userEntity = new UserEntity();
        userEntity.permissions = "READ,WRITE";
        List<GrantedAuthority> authorityList = new ArrayList<>();
        userEntity.getPermissionList().forEach(p -> {
            GrantedAuthority authority = new SimpleGrantedAuthority(p);
            authorityList.add(authority);
        });
        userPrincipal = new UserPrincipal(userEntity);

        assertEquals(authorityList, userPrincipal.getAuthorities());
    }

    @Test
    void getAuthoritiesRoles() {
        UserEntity userEntity = new UserEntity();
        userEntity.roles = "ADMIN,GUEST";
        List<GrantedAuthority> authorityList = new ArrayList<>();
        userEntity.getRoleList().forEach(r -> {
            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + r);
            authorityList.add(authority);
        });
        userPrincipal = new UserPrincipal(userEntity);

        assertEquals(authorityList, userPrincipal.getAuthorities());
    }

    @Test
    void username() {
        UserEntity userEntity = new UserEntity();
        userEntity.username = "TestUser";
        userPrincipal = new UserPrincipal(userEntity);
        assertEquals("TestUser", userPrincipal.getUsername());
    }

    @Test
    void password() {
        UserEntity userEntity = new UserEntity();
        userEntity.password = "PasswordTest";
        userPrincipal = new UserPrincipal(userEntity);
        userPrincipal.getPassword();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        assertNotEquals(bCryptPasswordEncoder.encode("PasswordTest"), userPrincipal.getPassword());
    }

    @Test
    void expired() {
        assertTrue(userPrincipal.isAccountNonExpired());
    }

    @Test
    void locked() {
        assertTrue(userPrincipal.isAccountNonLocked());
    }

    @Test
    void credentials() {
        assertTrue(userPrincipal.isCredentialsNonExpired());
    }

    @Test
    void enabled() {
        UserEntity userEntity = new UserEntity();
        userEntity.active = true;
        userPrincipal = new UserPrincipal(userEntity);

        assertTrue(userPrincipal.isEnabled());
    }

    @Test
    void disabled() {
        UserEntity userEntity = new UserEntity();
        userEntity.active = false;
        userPrincipal = new UserPrincipal(userEntity);

        assertFalse(userPrincipal.isEnabled());
    }
}
