package com.sasa.backend.service;

import com.sasa.backend.dto.product.CannabisProductDTO;
import com.sasa.backend.dto.product.ProductDTO;
import com.sasa.backend.entity.product.Product;
import java.util.List;

public interface ProductService {
    
    // Common methods for all product types
    List<ProductDTO> getAllProducts();
    ProductDTO getProductById(Long id);
    ProductDTO updateProduct(Long id, ProductDTO productDTO);
    void deleteProduct(Long id);
    List<ProductDTO> getProductsByCategory(Product.ProductCategory category);
    
    // Methods to create specific product types
    CannabisProductDTO createCannabisProduct(CannabisProductDTO cannabisProductDTO);
    // Add methods for other product types as needed

    List<CannabisProductDTO> getAllCannabisProducts();
    List<CannabisProductDTO> createCannabisProducts(List<CannabisProductDTO> cannabisProductDTOs);
}