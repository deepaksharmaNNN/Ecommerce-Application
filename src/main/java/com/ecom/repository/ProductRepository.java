package com.ecom.repository;

import com.ecom.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByIsActiveTrue();

    @Query("SELECT p FROM Product p WHERE p.isActive = TRUE AND (LOWER(p.name) " +
            "LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(p.description) " +
            "LIKE LOWER(CONCAT('%', :keyword, '%')))")
    List<Product> searchProducts(@Param("keyword") String keyword);
}
