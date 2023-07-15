package ru.kata.spring.boot_security.demo.clientControllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class ClientAdminController {

    @GetMapping("/admin")
    public String getUsers() {
        return "allUsers";
    }


}
