package com.sasa.backend.service;

import com.sasa.backend.dto.product.ProductDTO;
import com.sasa.backend.entity.product.Product;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProducts();

    ProductDTO getProductById(Long id);

    ProductDTO createProduct(ProductDTO productDTO);

    ProductDTO updateProduct(Long id, ProductDTO productDTO);

    void deleteProduct(Long id);

    List<ProductDTO> getProductsByCategory(Product.ProductCategory category);
}