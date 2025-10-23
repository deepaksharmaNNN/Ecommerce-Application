package com.ecom.service;

import com.ecom.dto.OrderResponse;
import com.ecom.enums.OrderStatus;
import com.ecom.mapper.OrderMapper;
import com.ecom.model.CartItem;
import com.ecom.model.Order;
import com.ecom.model.OrderItem;
import com.ecom.model.User;
import com.ecom.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartItemService cartItemService;
    private final UserService userService;
    private final OrderMapper orderMapper;

    public Optional<OrderResponse> placeOrder(Long userId) {
        // Validate for cart items
        List<CartItem> cartItems = cartItemService.getCartItemsByUserId(userId);
        if (cartItems.isEmpty()) {
            return Optional.empty();
        }
        // Validate for user
        Optional<User> userOpt = userService.getUserByIdInternal(userId);
        if (userOpt.isEmpty()) {
            return Optional.empty();
        }
        // Calculate total amount
        BigDecimal totalPrice = cartItems.stream()
                .map(CartItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        // Cart items to order items
        List<OrderItem> orderItems = cartItems.stream()
                .map(cartItem -> OrderItem.builder()
                        .product(cartItem.getProduct())
                        .quantity(cartItem.getQuantity())
                        .price(cartItem.getPrice())
                        .build())
                .toList();
        // Create order
        Order order = Order.builder()
                .user(userOpt.get())
                .totalAmount(totalPrice)
                .status(OrderStatus.CONFIRMED)
                .orderItems(orderItems)
                .build();
        for(OrderItem orderItem : orderItems){
            orderItem.setOrder(order);
        }
        // Save order
        Order savedOrder = orderRepository.save(order);
        // Clear cart items
        cartItemService.clearCartByUserId(userId);
        // Convert to OrderResponse
        return Optional.ofNullable(orderMapper.mapToOrderResponse(savedOrder));
    }
}
