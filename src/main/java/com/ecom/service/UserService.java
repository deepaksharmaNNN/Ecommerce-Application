package com.ecom.service;

import com.ecom.dto.UserRequest;
import com.ecom.dto.UserResponse;
import com.ecom.mapper.UserMapper;
import com.ecom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private  final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserResponse> getAllUser(){
        return userRepository.findAll().stream()
                .map(userMapper::mapToUserResponse)
                .toList();
    }

    public String createUser(UserRequest userRequest) {
        return userRepository.save(userMapper.mapToUser(userRequest)).getFirstName() + " created!";
    }
    public Optional<UserResponse> getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::mapToUserResponse);

    }
    public Boolean updateUser(Long id, UserRequest updatedUserRequest) {
        return  userRepository.findById(id).map(existingUser -> {
            existingUser.setFirstName(updatedUserRequest.getFirstName());
            existingUser.setLastName(updatedUserRequest.getLastName());
            existingUser.setEmail(updatedUserRequest.getEmail());
            existingUser.setPhone(updatedUserRequest.getPhone());
            if (updatedUserRequest.getAddress() != null) {
                if (existingUser.getAddress() == null) {
                    existingUser.setAddress(userMapper.mapToUser(updatedUserRequest).getAddress());
                } else {
                    existingUser.getAddress().setStreet(updatedUserRequest.getAddress().getStreet());
                    existingUser.getAddress().setCity(updatedUserRequest.getAddress().getCity());
                    existingUser.getAddress().setState(updatedUserRequest.getAddress().getState());
                    existingUser.getAddress().setCountry(updatedUserRequest.getAddress().getCountry());
                    existingUser.getAddress().setZipCode(updatedUserRequest.getAddress().getZipCode());
                }
            }
            userRepository.save(existingUser);
            return true;
        }).orElse(false);
    }
}
