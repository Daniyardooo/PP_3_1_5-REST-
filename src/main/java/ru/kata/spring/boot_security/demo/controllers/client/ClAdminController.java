package ru.kata.spring.boot_security.demo.controllers.client;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClAdminController {

    @GetMapping("/admin")
    public String getUsers() {
        return "allUsers";
    }


}
