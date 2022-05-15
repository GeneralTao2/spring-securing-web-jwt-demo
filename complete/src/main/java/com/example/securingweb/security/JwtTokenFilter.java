package com.example.securingweb.security;

import com.example.securingweb.model.JwtUserData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.securingweb.security.JwtConstants.HEADER_STRING;

@AllArgsConstructor
@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    JwtUserDataDecoder jwtUserDataDecoder;
    JwtUserDataValidator jwtUserDataValidator;
    UserDetailsAuthenticator userDetailsAuthenticator;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        JwtUserData jwtUserData = jwtUserDataDecoder.decode(req.getHeader(HEADER_STRING));

        if(jwtUserDataValidator.validate(jwtUserData)) {
            userDetailsAuthenticator.authenticate(jwtUserData, req);
        }

        chain.doFilter(req, res);
    }
}
