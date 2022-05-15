package com.example.securingweb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
