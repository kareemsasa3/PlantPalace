package com.sasa.backend.service;

import com.sasa.backend.constant.Constants;
import com.sasa.backend.dto.ProductDTO;
import com.sasa.backend.entity.Category;
import com.sasa.backend.entity.Product;
import com.sasa.backend.entity.Type;
import com.sasa.backend.exception.ResourceNotFoundException;
import com.sasa.backend.mapper.ProductMapper;
import com.sasa.backend.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                       .map(ProductMapper::toDTO)
                       .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProductDTO> getProductById(Long id) throws ResourceNotFoundException {
        return Optional.of(productRepository.findById(id)
                                .map(ProductMapper::toDTO)
                                .orElseThrow(() -> new ResourceNotFoundException(Constants.PRODUCT_NOT_FOUND_MESSAGE + id)));
    }

    @Override
    @Transactional
    public ProductDTO saveProduct(ProductDTO productDTO) {
        Product product = ProductMapper.toEntity(productDTO);
        Product savedProduct = productRepository.save(product);
        return ProductMapper.toDTO(savedProduct);
    }

    @Override
    @Transactional
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) throws ResourceNotFoundException {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Constants.PRODUCT_NOT_FOUND_MESSAGE + id));
        
        Product updatedProduct = ProductMapper.toEntity(productDTO);
        updatedProduct.setId(existingProduct.getId());
        
        Product savedProduct = productRepository.save(updatedProduct);
        return ProductMapper.toDTO(savedProduct);
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) throws ResourceNotFoundException {
        Product product = productRepository.findById(id)
                                           .orElseThrow(() -> new ResourceNotFoundException(Constants.PRODUCT_NOT_FOUND_MESSAGE + id));
        productRepository.delete(product);
    }

    @Override
    public List<ProductDTO> getProductsByCategory(Category category) {
        List<Product> products = productRepository.findByCategory(category);
        return products.stream()
                       .map(ProductMapper::toDTO)
                       .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getProductsByType(Type type) {
        List<Product> products = productRepository.findByType(type);
        return products.stream()
                       .map(ProductMapper::toDTO)
                       .collect(Collectors.toList());
    }

    @Override
    public Page<ProductDTO> getProductsWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findAll(pageable);
        return productPage.map(ProductMapper::toDTO);
    }
}
