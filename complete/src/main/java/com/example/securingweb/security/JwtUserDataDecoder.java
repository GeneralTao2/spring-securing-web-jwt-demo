package com.example.securingweb.security;

import com.example.securingweb.model.JwtUserData;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.example.securingweb.security.JwtConstants.TOKEN_PREFIX;

@AllArgsConstructor
@Component
public class JwtUserDataDecoder {
    private final JwtTokenUtil jwtTokenUtil;

    protected final Log logger = LogFactory.getLog(getClass());

    public JwtUserData decode(String rawToken) {
        String email = null;
        List<SimpleGrantedAuthority> roles = null;

        if (rawToken != null && rawToken.startsWith(TOKEN_PREFIX)) {
            String authToken = rawToken.replace(TOKEN_PREFIX, "");
            try {
                email = jwtTokenUtil.getUsernameFromToken(authToken);
                roles = jwtTokenUtil.getRoleFromToken(authToken);
            } catch (IllegalArgumentException e) {
                logger.error("an error occured during getting email from token", e);
            } catch (ExpiredJwtException e) {
                logger.warn("the token is expired and not valid anymore", e);
            } catch (SignatureException e) {
                logger.error("Authentication Failed. Username or Password not valid.");
            }
        } else {
            logger.warn("couldn't find bearer string, will ignore the token");
        }

        return new JwtUserData(email, roles);
    }
}
