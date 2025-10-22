package com.ecom.controller;

import com.ecom.dto.ProductRequest;
import com.ecom.dto.ProductResponse;
import com.ecom.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/product") // http://localhost:8080/api/products/product
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest) {
        return new ResponseEntity<>(productService.createProduct(productRequest), HttpStatus.CREATED);
    }

    @PutMapping("/product/{id}") // http://localhost:8080/api/products/product/1
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id, @RequestBody ProductRequest productRequest) {
        return productService.updateProduct(id, productRequest)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all") // http://localhost:8080/api/products/all
    public  ResponseEntity<List<ProductResponse>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @DeleteMapping("/product/{id}") // http://localhost:8080/api/products/product/1
    public ResponseEntity<Boolean> deleteProduct(@PathVariable Long id) {
        return new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.OK);
    }

    @GetMapping("/search") // http://localhost:8080/api/products/search
    public ResponseEntity<List<ProductResponse>> searchProducts(@RequestParam String keyword) {
        return new ResponseEntity<>(productService.searchProducts(keyword), HttpStatus.OK);
    }

}
