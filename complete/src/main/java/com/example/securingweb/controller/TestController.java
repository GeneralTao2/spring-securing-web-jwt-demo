package com.example.securingweb.controller;

import com.example.securingweb.model.Role;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
public class TestController {

    @GetMapping("/hey")
    String hey() {
        return "Hey Hey!";
    }

    @GetMapping("/yo")
    String yo() {
        return "Yo!";
    }
}
