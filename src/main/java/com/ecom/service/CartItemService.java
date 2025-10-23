package com.ecom.service;

import com.ecom.dto.CartItemRequest;
import com.ecom.model.CartItem;
import com.ecom.model.Product;
import com.ecom.model.User;
import com.ecom.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartItemService {
    private final CartItemRepository cartItemRepository;
    private final ProductService productService;
    private final UserService userService;

    public Boolean addItemToCart(Long userId, CartItemRequest cartItemRequest) {
        //Look for product
        Optional<Product> productOpt = productService.getProductByIdInternal(cartItemRequest.getProductId());
        if (productOpt.isEmpty()) return false;
        //Look for quantity
        if(productOpt.get().getStockQuantity() < cartItemRequest.getQuantity()) return false;
        //Look for user
        Optional<User> userOpt = userService.getUserByIdInternal(userId);
        if (userOpt.isEmpty()) return false;

        CartItem existingCartItem = cartItemRepository.findByUserAndProduct(userOpt.get(), productOpt.get());
        if (existingCartItem != null) {
            // Update quantity if item already exists in cart
            existingCartItem.setQuantity(existingCartItem.getQuantity() + cartItemRequest.getQuantity());
            existingCartItem.setPrice(productOpt.get().getPrice().multiply(BigDecimal.valueOf(existingCartItem.getQuantity())));
            cartItemRepository.save(existingCartItem);
        }else {
            // Create new cart item
            CartItem cartItem = new CartItem();
            cartItem.setUser(userOpt.get());
            cartItem.setProduct(productOpt.get());
            cartItem.setQuantity(cartItemRequest.getQuantity());
            cartItem.setPrice(productOpt.get().getPrice().multiply(BigDecimal.valueOf(cartItemRequest.getQuantity())));
            cartItemRepository.save(cartItem);
        }
        return true;
    }
}
