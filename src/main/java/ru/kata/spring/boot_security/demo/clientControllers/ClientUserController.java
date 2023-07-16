package ru.kata.spring.boot_security.demo.clientControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClientUserController {
    @GetMapping("/user")
    public String getUserInfo() {
        return "user";
    }

}
