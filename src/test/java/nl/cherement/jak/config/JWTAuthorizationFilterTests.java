package nl.cherement.jak.config;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class JWTAuthorizationFilterTests {

    JWTAuthorizationFilter  jwtAuthorizationFilter = new JWTAuthorizationFilter(authentication -> null);

    @Mock
    HttpServletRequest req;

    @Mock
    private FilterChain chain;

    @Mock
    private HttpServletResponse res;

    @BeforeEach void initialize(){
        req = mock(HttpServletRequest.class);
        doReturn("Bearer test").when(req).getHeader("Authorization");
        doReturn("Bearer.test").when(req).getHeader("sec-websocket-protocol");
        doReturn("Bearer%20test").when(req).getQueryString();
    }

    @Test
    void getTokenAndAuthorization() {
        String token = jwtAuthorizationFilter.getToken(req);
        assertEquals("test", token);

        doReturn(null).when(req).getHeader("Authorization");
        token = jwtAuthorizationFilter.getToken(req);
        assertEquals("test", token);
    }
    
    @Test
    void doFilterInternal() throws IOException, ServletException {
        jwtAuthorizationFilter.doFilterInternal(req,res,chain);
        verify(req,atLeastOnce()).getHeader("Authorization");
    }
}
