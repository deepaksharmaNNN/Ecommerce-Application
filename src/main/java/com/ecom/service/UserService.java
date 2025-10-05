package com.ecom.service;

import com.ecom.Models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    public List<User> userList= new ArrayList<>();

    public List<User> getAllUser(){
        return userList;
    }

    public List<User> createUser(User user) {
        userList.add(user);
        return userList;
    }
    public Optional<User> getUserById(Long id) {
        return userList.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }
    public User updateUser(Long id, User updatedUser) {
        return getUserById(id).map(user -> {
            user.setFirstName(updatedUser.getFirstName());
            user.setLastName(updatedUser.getLastName());
            return user;
        }).orElse(null);
    }
}
