package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/admin")
public class AdminController {

    private final UserService userServiceImpl;

    public AdminController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("")
    @ResponseBody
    public List<User> getUsers() {
        return userServiceImpl.getAllUsers();
    }


    @GetMapping("/findOne/{id}")
    @ResponseBody
    public User findOne(@PathVariable Long id) {
        return userServiceImpl.findUserById(id);
    }


    @PutMapping("/update")
    @ResponseBody
    public String updateUserById(@RequestBody User user, Principal principal) {

        if (principal.getName().equals(userServiceImpl.findUserById(user.getId()).getUsername()) && !principal.getName().equals(user.getUsername())) {
            userServiceImpl.updateUserById(user.getId(), user);
            return "redirect:/login";
        }
        Optional<User> userFromDB = userServiceImpl.findByUsername(user.getUsername());
        if(userFromDB.isPresent()) {
            if (userFromDB.get().getUsername().equals(principal.getName())) {
                return "exist";
            }
        }
        userServiceImpl.updateUserById(user.getId(), user);
        return "ok";
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public String removeUserById(@RequestParam Long id) {
        userServiceImpl.deleteUserById(id);
        return "ok";
    }

    @PostMapping("/new")
    @ResponseBody
    public String createUser(@RequestBody User user) {
        Optional<User> userFromDB = userServiceImpl.findByUsername(user.getUsername());
        if (userFromDB.isPresent()) {
            return "exist";
        }
        userServiceImpl.saveUser(user);
        return "ok";
    }

}


