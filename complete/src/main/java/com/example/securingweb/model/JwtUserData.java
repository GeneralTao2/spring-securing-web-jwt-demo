package com.example.securingweb.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class JwtUserData {
    String username;
    List<SimpleGrantedAuthority> roles;
}
