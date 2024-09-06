package com.sasa.backend.repository;

import com.sasa.backend.entity.product.Product;
import com.sasa.backend.entity.product.Product.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Method to find products by category
    List<Product> findByProductCategory(ProductCategory category);
}