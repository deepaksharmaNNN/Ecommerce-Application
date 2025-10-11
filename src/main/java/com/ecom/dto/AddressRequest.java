package com.ecom.dto;

import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class AddressRequest {
    String street;
    String city;
    String state;
    String zipCode;
    String country;
}