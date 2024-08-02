package com.sasa.backend.service;

import com.sasa.backend.dto.ProductDTO;
import com.sasa.backend.entity.Category;
import com.sasa.backend.entity.Type;
import com.sasa.backend.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<ProductDTO> getAllProducts();

    Optional<ProductDTO> getProductById(Long id) throws ResourceNotFoundException;

    ProductDTO saveProduct(ProductDTO productDTO);

    ProductDTO updateProduct(Long id, ProductDTO productDTO) throws ResourceNotFoundException;

    void deleteProduct(Long id) throws ResourceNotFoundException;

    List<ProductDTO> getProductsByCategory(Category category);

    List<ProductDTO> getProductsByType(Type type);

    Page<ProductDTO> getProductsWithPagination(int page, int size);
}
