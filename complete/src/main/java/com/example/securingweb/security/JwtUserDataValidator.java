package com.example.securingweb.security;

import com.example.securingweb.model.JwtUserData;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class JwtUserDataValidator {
    private UserDetailsService userDetailsService;

    boolean validate(JwtUserData jwtUserData) {

        if (jwtUserData.getEmail() == null) {
            return false;
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtUserData.getEmail());

        // todo validate properly
        if (userDetails == null) {
            return false;
        }

        //TODO: do something with warning
        if (!jwtUserData.getRoles().containsAll(userDetails.getAuthorities())) {
            return false;
        }

        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            return false;
        }

        return true;
    }
}
