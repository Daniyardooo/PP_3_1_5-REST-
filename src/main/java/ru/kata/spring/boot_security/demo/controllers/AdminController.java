package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/admin")
public class AdminController {

    private final UserServiceImpl userServiceImpl;

    public AdminController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("")
    @ResponseBody
    public List<User> getUsers(Model model, Principal principal) {
//        model.addAttribute("users", userServiceImpl.getAllUsers());
//        String principalName = principal.getName();
//        User user = userServiceImpl.findByUsername(principalName).get();
//        int nextUserId = userServiceImpl.getAllUsers().size() + 1;
//        model.addAttribute("user", user);
//        model.addAttribute("nextUserId", nextUserId);
        return userServiceImpl.getAllUsers();
    }


    @GetMapping("/findOne/{id}")
    @ResponseBody
    public User findOne(@PathVariable Long id) {
        return userServiceImpl.findUserById(id);
    }


    @PutMapping("/update")
    @ResponseBody
    public String updateUserById(@RequestBody User user) {

//        if (principal.getName().equals(userServiceImpl.findUserById(user.getId()).getUsername()) && !principal.getName().equals(user.getUsername())) {
//            userServiceImpl.updateUserById(user.getId(), user);
//            return "redirect:/login";
//        }
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
//        if (userFromDB.isPresent()) {
//            return "redirect:/admin/users?error=User already exists ";
//        }
        userServiceImpl.saveUser(user);
        return "ok";
    }

}


