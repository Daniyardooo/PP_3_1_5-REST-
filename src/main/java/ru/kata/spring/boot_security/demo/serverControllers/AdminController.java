package ru.kata.spring.boot_security.demo.serverControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/admin")
public class AdminController {

    private UserService userServiceImpl;


    @Autowired
    public void setUserServiceImpl(UserService userServiceImpl) {
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
    public String updateUserById(@RequestBody User user, Principal principal, HttpServletRequest request, HttpServletResponse response) {

        if (principal.getName().equals(userServiceImpl.findUserById(user.getId()).getUsername()) && !principal.getName().equals(user.getUsername())) {
            userServiceImpl.updateUserById(user.getId(), user);
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null) {
                new SecurityContextLogoutHandler().logout(request, response, auth);
            }
            return "principalNameEdit";
        }
        Optional<User> userFromDB = userServiceImpl.findByUsername(user.getUsername());
        if (userFromDB.isPresent()) {
            if (userFromDB.get().getUsername().equals(principal.getName()) && !userFromDB.get().getUsername().equals(user.getUsername())) {
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


