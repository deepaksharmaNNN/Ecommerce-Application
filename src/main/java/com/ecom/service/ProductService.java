package com.ecom.service;

import com.ecom.dto.ProductRequest;
import com.ecom.dto.ProductResponse;
import com.ecom.mapper.ProductMapper;
import com.ecom.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

}
