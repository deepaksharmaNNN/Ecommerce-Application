package com.ecom.mapper;

import com.ecom.Models.Address;
import com.ecom.Models.User;
import com.ecom.dto.AddressResponse;
import com.ecom.dto.UserRequest;
import com.ecom.dto.UserResponse;
import com.ecom.enums.UserRole;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponse mapToUserResponse(User user){
        return UserResponse.builder()
                .id(String.valueOf(user.getId()))
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .phone(user.getPhone())
                .address(mapToAddressResponse(user))
                .build();
    }
    private AddressResponse mapToAddressResponse(User user){
        if(user.getAddress() == null) return null;
        return AddressResponse.builder()
                .street(user.getAddress().getStreet())
                .city(user.getAddress().getCity())
                .state(user.getAddress().getState())
                .country(user.getAddress().getCountry())
                .zipCode(user.getAddress().getZipCode())
                .build();
    }
    public User mapToUser(UserRequest userRequest){
        return User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .email(userRequest.getEmail())
                .phone(userRequest.getPhone())
                .role(UserRole.CUSTOMER)
                .address(mapToAddress(userRequest))
                .build();
    }
    private Address mapToAddress(UserRequest userRequest){
        if(userRequest.getAddress() == null) return null;
        return Address.builder()
                .street(userRequest.getAddress().getStreet())
                .city(userRequest.getAddress().getCity())
                .state(userRequest.getAddress().getState())
                .country(userRequest.getAddress().getCountry())
                .zipCode(userRequest.getAddress().getZipCode())
                .build();
    }
}
