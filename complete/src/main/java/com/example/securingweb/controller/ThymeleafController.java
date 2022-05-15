package com.example.securingweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ThymeleafController {
    //TODO redirection doesnt work
    @GetMapping(value = "/login")
    public String login(Model model) {
        model.addAttribute("name", "guest");
        return "login";
    }

    @RequestMapping(value = {"/home", "/"}, method = RequestMethod.GET)
    public String home() {
        return "home";
    }

    @GetMapping(value = "/hello")
    public String hello() {
        return "hello";
    }


}
