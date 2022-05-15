package com.example.securingweb.config;

import com.example.securingweb.model.JwtUserData;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.example.securingweb.config.Constants.HEADER_STRING;
import static com.example.securingweb.config.Constants.TOKEN_PREFIX;

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
