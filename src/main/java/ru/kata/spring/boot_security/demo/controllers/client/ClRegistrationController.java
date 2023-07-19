package ru.kata.spring.boot_security.demo.controllers.client;

import org.springframework.web.bind.annotation.GetMapping;


public class ClRegistrationController {


    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }
}

