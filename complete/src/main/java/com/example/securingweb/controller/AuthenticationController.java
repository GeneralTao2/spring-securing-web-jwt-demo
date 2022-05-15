package com.example.securingweb.controller;

import com.example.securingweb.config.JwtTokenUtil;
import com.example.securingweb.model.ApiResponse;
import com.example.securingweb.model.AuthToken;
import com.example.securingweb.model.LoginUser;
import com.example.securingweb.model.User;
import com.example.securingweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/login")
    public ApiResponse register(@RequestBody LoginUser loginUser) throws AuthenticationException {

        /*authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword())
        );*/
        final User user = userRepository.findByUsername(loginUser.getUsername()).orElseThrow();
        final String token = jwtTokenUtil.generateToken(user);
        return new ApiResponse(200, "success", new AuthToken(token, user.getUsername()));
    }


}
