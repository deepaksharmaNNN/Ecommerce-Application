package com.ecom.mapper;

import com.ecom.dto.OrderResponse;
import com.ecom.model.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public OrderResponse mapToOrderResponse(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .totalAmount(order.getTotalAmount())
                .orderStatus(order.getStatus())
                .orderItems(order.getOrderItems().stream()
                        .map(OrderItemMapper::mapToOrderItemResponse)
                        .toList())
                .createdAt(order.getCreatedAt())
                .build();
    }
}
