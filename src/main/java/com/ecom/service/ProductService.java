package com.ecom.service;

import com.ecom.dto.ProductRequest;
import com.ecom.dto.ProductResponse;
import com.ecom.mapper.ProductMapper;
import com.ecom.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private  final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductResponse createProduct(ProductRequest productRequest){
        return productMapper.mapToProductResponse(
                productRepository.save(
                        productMapper.mapToProduct(productRequest)
                )
        );
    }

    public Optional<ProductResponse> updateProduct(Long id, ProductRequest productRequest) {
        return Optional.ofNullable(productRepository.findById(id).map(_ ->
                        productMapper.mapToProductResponse(productRepository.save(productMapper.mapToProductWithId(id, productRequest))))
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id)));
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findByIsActiveTrue().stream()
                .map(productMapper::mapToProductResponse)
                .collect(Collectors.toList());
    }

    // Soft delete implementation with Exception handling
    public Boolean deleteProduct(Long id) {
        return productRepository.findById(id).map(product -> {
            product.setIsActive(Boolean.FALSE);
            productRepository.save(product);
            return Boolean.TRUE;
        }).orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }
}
