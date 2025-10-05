package com.ecom.Models;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    Long id;
    String firstName;
    String lastName;
}
