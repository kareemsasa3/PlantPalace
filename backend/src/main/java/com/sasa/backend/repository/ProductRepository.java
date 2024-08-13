package com.sasa.backend.repository;

import com.sasa.backend.entity.Category;
import com.sasa.backend.entity.Product;
import com.sasa.backend.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findByCategory(Category category);
    List<Product> findByType(Type type);
    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);
    List<Product> findByNameContainingIgnoreCaseAndCategory(String name, Category category);
    List<Product> findByNameContainingIgnoreCaseAndType(String name, Type type);
    List<Product> findByCategoryAndType(Category category, Type type);
    List<Product> findByNameContainingIgnoreCaseAndCategoryAndType(String name, Category category, Type type);
}

