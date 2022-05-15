package com.example.securingweb.config;

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

        if(jwtUserData.getUsername() == null) {
            return false;
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtUserData.getUsername());

         // todo validate properly
        if(userDetails == null) {
            return false;
        }

        if(!jwtUserData.getRoles().containsAll(userDetails.getAuthorities())) {
            return false;
        }

        if(SecurityContextHolder.getContext().getAuthentication() != null) {
            return false;
        }

        return true;
    }
}
