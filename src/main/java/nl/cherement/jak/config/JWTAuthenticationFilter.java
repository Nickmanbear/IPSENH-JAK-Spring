package nl.cherement.jak.config;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.cherement.jak.entity.UserEntity;
import nl.cherement.jak.exception.AuthenticationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static nl.cherement.jak.config.SecurityConstants.*;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) {
        try {
            UserEntity credentials = new ObjectMapper().readValue(req.getInputStream(), UserEntity.class);

            return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    credentials.username,
                    credentials.password,
                    new ArrayList<>())
            );
        } catch (IOException e) {
            throw new AuthenticationException(e + "Not Authenticated");
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res,
                                            FilterChain chain, Authentication auth) {
        String token = JWT.create()
            .withSubject(((User) auth.getPrincipal()).getUsername())
            .withClaim("active", ((User) auth.getPrincipal()).isEnabled())
            .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .sign(HMAC512(SecurityConstants.getInstance().getSecret().getBytes()));
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
    }
}
