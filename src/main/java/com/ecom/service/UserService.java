package com.ecom.service;

import com.ecom.Models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
}
