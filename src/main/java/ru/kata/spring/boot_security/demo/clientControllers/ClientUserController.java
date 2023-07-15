package ru.kata.spring.boot_security.demo.clientControllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kata.spring.boot_security.demo.models.User;

import java.security.Principal;

@Controller
public class ClientUserController {
    @GetMapping("/user")
    public String getUserInfo() {
        return "user";
    }

}
