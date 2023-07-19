package ru.kata.spring.boot_security.demo.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserService;

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
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(userServiceImpl.getAllUsers(), HttpStatus.OK);
    }


    @GetMapping("/findOne/{id}")
    @ResponseBody
    public ResponseEntity<User> findOne(@PathVariable Long id) {
        return new ResponseEntity<>(userServiceImpl.findUserById(id), HttpStatus.OK);
    }


    @PutMapping("/update")
    @ResponseBody
    public ResponseEntity<String> updateUserById(@RequestBody User user, Principal principal) {
        String usernameFromDB = userServiceImpl.findUserById(user.getId()).getUsername();
        if (usernameFromDB.equals(user.getUsername())) {
            userServiceImpl.updateUserById(user.getId(), user);
            new ResponseEntity<>("ok", HttpStatus.OK);
        }

        if (userServiceImpl.findByUsername(user.getUsername()).isPresent()) {
            return new ResponseEntity<>("exist", HttpStatus.CONFLICT);
        }
        userServiceImpl.updateUserById(user.getId(), user);
        return usernameFromDB.equals(principal.getName()) ? new ResponseEntity<>("principalNameEdit", HttpStatus.OK) : new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public ResponseEntity<String> removeUserById(@RequestParam Long id) {
        userServiceImpl.deleteUserById(id);
        return new ResponseEntity<>("ok", HttpStatus.OK);

    }

    @PostMapping("/new")
    @ResponseBody
    public ResponseEntity<String> createUser(@RequestBody User user) {
        Optional<User> userFromDB = userServiceImpl.findByUsername(user.getUsername());
        if (userFromDB.isPresent()) {
            return new ResponseEntity<>("exist", HttpStatus.CONFLICT);
        }
        userServiceImpl.saveUser(user);
        return new ResponseEntity<>("ok", HttpStatus.OK);

    }
}


