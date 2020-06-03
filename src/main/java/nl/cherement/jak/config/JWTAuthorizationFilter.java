package nl.cherement.jak.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static nl.cherement.jak.config.SecurityConstants.*;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        String token = getToken(req);

        if (token != null) {
            SecurityContextHolder.getContext().setAuthentication(getAuthentication(token));
        }

        chain.doFilter(req, res);
    }

    String getToken(HttpServletRequest req) {
        String token = req.getHeader(HEADER_STRING);

        if (token == null || !token.startsWith(TOKEN_PREFIX)) {
            String protocols = req.getHeader(WEBSOCKET_HEADER_STRING);
            if (protocols == null || !protocols.contains(WEBSOCKET_TOKEN_PREFIX)) {
                return null;
            }
            return protocols.substring(protocols.indexOf(WEBSOCKET_TOKEN_PREFIX))
                    .replace(WEBSOCKET_TOKEN_PREFIX, "");
        }
        return token.replace(TOKEN_PREFIX, "");
    }

    UsernamePasswordAuthenticationToken getAuthentication(String token) {
        try {
            String user = JWT.require(Algorithm.HMAC512(SecurityConstants.getInstance().getSecret().getBytes()))
                    .build()
                    .verify(token)
                    .getSubject();

            return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
        } catch (Exception e) {
            return null;
        }
    }
}
