package com.ecom.mapper;

import com.ecom.dto.ProductRequest;
import com.ecom.dto.ProductResponse;
import com.ecom.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product mapToProduct(ProductRequest productRequest){
        return Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .stockQuantity(productRequest.getStockQuantity())
                .category(productRequest.getCategory())
                .imageUrl(productRequest.getImageUrl())
                .build();
    }
    public ProductResponse mapToProductResponse(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stockQuantity(product.getStockQuantity())
                .productCategory(product.getCategory())
                .imageUrl(product.getImageUrl())
                .isActive(product.getIsActive())
                .build();
    }
}
