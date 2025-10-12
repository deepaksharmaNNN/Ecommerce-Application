package com.ecom.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class AddressResponse {
    String street;
    String city;
    String state;
    String zipCode;
    String country;
}