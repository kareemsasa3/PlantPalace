package com.sasa.backend.repository;

import com.sasa.backend.entity.Category;
import com.sasa.backend.entity.Product;
import com.sasa.backend.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(Category category);
    List<Product> findByType(Type type);
}
