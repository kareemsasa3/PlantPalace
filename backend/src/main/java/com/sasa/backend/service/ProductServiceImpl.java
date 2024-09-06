package com.sasa.backend.service;

import com.sasa.backend.dto.product.ProductDTO;
import com.sasa.backend.entity.product.Product;
import com.sasa.backend.repository.ProductRepository;
import com.sasa.backend.util.mapper.product.ProductMapper;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + id));
        return productMapper.toDTO(product);
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product productEntity = productMapper.toEntity(productDTO);
        Product savedProduct = productRepository.save(productEntity);
        return productMapper.toDTO(savedProduct);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + id));

        // Update fields
        existingProduct.setName(productDTO.getName());
        existingProduct.setBrand(productDTO.getBrand());
        existingProduct.setPrice(productDTO.getPrice());
        existingProduct.setQuantity(productDTO.getQuantity());
        existingProduct.setImage(productDTO.getImage());
        existingProduct.setDescription(productDTO.getDescription());

        Product updatedProduct = productRepository.save(existingProduct);
        return productMapper.toDTO(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDTO> getProductsByCategory(Product.ProductCategory category) {
        List<Product> products = productRepository.findByProductCategory(category);
        return products.stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }
}