package com.example.securingweb.security;

import com.example.securingweb.model.JwtUserData;
import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@AllArgsConstructor
public class UserDetailsAuthenticator {
    private UserDetailsService userDetailsService;

    protected final Log logger = LogFactory.getLog(getClass());

    void authenticate(JwtUserData jwtUserData, HttpServletRequest req) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtUserData.getEmail());

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null,
                        userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
        logger.info("authenticated user " + userDetails.getUsername() + ", setting security context");
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
