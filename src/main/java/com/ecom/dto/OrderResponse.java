package com.ecom.dto;

import com.ecom.enums.OrderStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponse {
    Long id;
    BigDecimal totalAmount;
    OrderStatus orderStatus;
    List<OrderItemResponse> orderItems;
    LocalDateTime createdAt;
}
