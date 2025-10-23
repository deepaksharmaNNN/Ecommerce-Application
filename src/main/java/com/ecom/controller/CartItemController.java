package com.ecom.controller;

import com.ecom.dto.CartItemRequest;
import com.ecom.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart-items")
public class CartItemController {

    private  final CartItemService cartItemService;


    @PostMapping("/add") // http://localhost:8080/api/cart-items/add
    public ResponseEntity<String> addItemToCart(@RequestHeader("X-User-Id") Long userId, @RequestBody CartItemRequest cartItemRequest) {
        Boolean isAdded = cartItemService.addItemToCart(userId, cartItemRequest);
        if (isAdded) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Item added successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add item to cart");
        }
    }
    @DeleteMapping("/remove/{productId}") // http://localhost:8080/api/cart-items/remove/{productId}
    public ResponseEntity<String> removeItemFromCart(@RequestHeader("X-User-Id") Long userId, @PathVariable Long productId) {
        Boolean result = cartItemService.deleteItemFromCart(userId, productId);
        return result ? ResponseEntity.status(HttpStatus.OK).body("Item removed successfully")
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to remove item from cart");
    }
}
