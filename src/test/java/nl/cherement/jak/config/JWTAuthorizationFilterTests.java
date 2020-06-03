package nl.cherement.jak.config;


import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@SpringBootTest
class JWTAuthorizationFilterTests {

    JWTAuthorizationFilter  jwtAuthorizationFilter = new JWTAuthorizationFilter(authentication -> null);

    @Mock
    HttpServletRequest req;

    @Test
    void getTokenAndAuthorization() {
        req = mock(HttpServletRequest.class);
        doReturn("Bearer test").when(req).getHeader("Authorization");
        doReturn("Bearer.test").when(req).getHeader("sec-websocket-protocol");

        String token = jwtAuthorizationFilter.getToken(req);
        assertEquals("test", token);
        assertNull(jwtAuthorizationFilter.getAuthentication(token));
    }
}
