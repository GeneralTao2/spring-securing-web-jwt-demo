package com.example.securingweb.config;

import com.example.securingweb.model.JwtUserData;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.example.securingweb.config.Constants.TOKEN_PREFIX;

@AllArgsConstructor
@Component
public class JwtUserDataDecoder {
    private final JwtTokenUtil jwtTokenUtil;

    protected final Log logger = LogFactory.getLog(getClass());

    public JwtUserData decode(String rawToken) {
        String authToken = null;
        String username = null;
        List<SimpleGrantedAuthority> roles = null;

        if (rawToken != null && rawToken.startsWith(TOKEN_PREFIX)) {
            authToken = rawToken.replace(TOKEN_PREFIX, "");
            try {
                username = jwtTokenUtil.getUsernameFromToken(authToken);
                roles = jwtTokenUtil.getRoleFromToken(authToken);
            } catch (IllegalArgumentException e) {
                logger.error("an error occured during getting username from token", e);
            } catch (ExpiredJwtException e) {
                logger.warn("the token is expired and not valid anymore", e);
            } catch (SignatureException e) {
                logger.error("Authentication Failed. Username or Password not valid.");
            }
        } else {
            logger.warn("couldn't find bearer string, will ignore the token");
        }

        return new JwtUserData(username, roles);
//
//
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//
//            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//
//            if (jwtTokenUtil.validateToken(authToken, userDetails)) {
//                //Role role = jwtTokenUtil.getRoleFromToken(authToken);
//                // todo catch
//                UsernamePasswordAuthenticationToken authentication =
//                        new UsernamePasswordAuthenticationToken(userDetails, null,
//                                roles);
//                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
//                logger.info("authenticated user " + username + ", setting security context");
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            }
//        }
    }
}
