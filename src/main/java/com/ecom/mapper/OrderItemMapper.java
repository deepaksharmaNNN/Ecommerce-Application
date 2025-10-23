package com.ecom.mapper;

import com.ecom.dto.OrderItemResponse;
import com.ecom.model.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {
    public static OrderItemResponse mapToOrderItemResponse(OrderItem orderItem) {
        return OrderItemResponse.builder()
                .id(orderItem.getId())
                .productId(orderItem.getProduct().getId())
                .quantity(orderItem.getQuantity())
                .price(orderItem.getPrice())
                .build();
    }
}
