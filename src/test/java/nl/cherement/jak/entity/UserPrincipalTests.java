package nl.cherement.jak.entity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        userEntity.setPermissions("READ,WRITE");
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
        userEntity.setRoles("ADMIN,GUEST");
        List<GrantedAuthority> authorityList = new ArrayList<>();
        userEntity.getRoleList().forEach(r -> {
            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + r);
            authorityList.add(authority);
        });
        userPrincipal = new UserPrincipal(userEntity);

        assertEquals(authorityList, userPrincipal.getAuthorities());
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
        userEntity.setActive(1);
        userPrincipal = new UserPrincipal(userEntity);

        assertTrue(userPrincipal.isEnabled());
    }
}
