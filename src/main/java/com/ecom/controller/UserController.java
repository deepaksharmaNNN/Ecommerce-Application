package com.ecom.controller;

import com.ecom.Models.User;
import com.ecom.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users") // localhost:8080/users
    public List<User> getAllUser(){
        return userService.getAllUser();
    }
    @PostMapping("/users") // localhost:8080/users
    public List<User> createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
    @GetMapping("/user/{id}") // localhost:8080/user/1
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

}
