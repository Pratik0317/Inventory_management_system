package com.texas.inventorymanagementsystem.controller;

import com.texas.inventorymanagementsystem.model.User;
import com.texas.inventorymanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {

        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public User loginUser(@RequestBody User user) {
        return  userService.loginUser(user.getUsername(), user.getPassword());
    }
}
