package com.ecom.controller;


import com.ecom.dto.UserRequest;
import com.ecom.dto.UserResponse;
import com.ecom.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @GetMapping("/users") // http://localhost:8080/api/users
    public ResponseEntity<List<UserResponse>> getAllUser(){
        return new  ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }
    @PostMapping("/users") // http://localhost:8080/api/users
    public ResponseEntity<String> createUser(@RequestBody UserRequest userRequest) {
        return new ResponseEntity<>(userService.createUser(userRequest), HttpStatus.CREATED);
    }
    @GetMapping("/user/{id}") // http://localhost:8080/api/user/1
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PutMapping("/user/{id}") // http://localhost:8080/api/user/1
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserRequest updatedUserRequest) {
        String responseMessage = userService.updateUser(id, updatedUserRequest).toString();
        if (responseMessage.equals("true")) {
            return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

    }

}
