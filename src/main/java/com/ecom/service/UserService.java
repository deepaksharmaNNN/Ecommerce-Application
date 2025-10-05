package com.ecom.service;

import com.ecom.Models.User;
import com.ecom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private  final UserRepository userRepository;

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public List<User> createUser(User user) {
        return userRepository.saveAll(List.of(user));
    }
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);

    }
    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setFirstName(updatedUser.getFirstName());
                    user.setLastName(updatedUser.getLastName());
                    return userRepository.save(user);
                })
                .orElse(null);
    }
}
