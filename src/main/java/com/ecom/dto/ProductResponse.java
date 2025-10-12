package com.ecom.dto;

import com.ecom.enums.ProductCategory;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PACKAGE)
public class ProductResponse {
    Long id;
    String name;
    String description;
    BigDecimal price;
    Integer stockQuantity;
    ProductCategory productCategory;
    String imageUrl;
    Boolean isActive;
}
