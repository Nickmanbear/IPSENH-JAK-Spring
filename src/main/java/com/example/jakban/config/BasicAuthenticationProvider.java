package com.example.jakban.config;

import com.example.jakban.service.UserPrincipalDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BasicAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;

    @Autowired
    public BasicAuthenticationProvider(UserPrincipalDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) {

        String username = authentication.getName();
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        String password = (String) authentication.getCredentials();
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails == null) {
            throw new BadCredentialsException("username not found");
        }
        if (!bcrypt.matches(password, userDetails.getPassword())) {
            System.out.println("password incorrect");
        }
        if (!userDetails.isAccountNonExpired()) {
            throw new CredentialsExpiredException("account expired");
        }
        if (!userDetails.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException("password expired");
        }
        if (!userDetails.isAccountNonLocked()) {
            throw new LockedException("account locked");
        }


        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
