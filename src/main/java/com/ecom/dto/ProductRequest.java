package com.ecom.dto;

import com.ecom.enums.ProductCategory;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@FieldDefaults(level = AccessLevel.PACKAGE)
public class ProductRequest {
    String name;
    String description;
    BigDecimal price;
    Integer stockQuantity;
    ProductCategory category;
    String imageUrl;
}
