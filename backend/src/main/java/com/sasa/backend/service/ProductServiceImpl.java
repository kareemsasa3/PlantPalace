package com.sasa.backend.service;

import com.sasa.backend.dto.product.CannabisProductDTO;
import com.sasa.backend.dto.product.ProductDTO;
import com.sasa.backend.entity.product.CannabisProduct;
import com.sasa.backend.entity.product.Product;
import com.sasa.backend.repository.ProductRepository;
import com.sasa.backend.util.mapper.product.CannabisProductMapper;
import com.sasa.backend.util.mapper.product.ProductMapper;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + id));
        return ProductMapper.toDTO(product);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + id));

        // Update fields
        existingProduct.setName(productDTO.getName());
        existingProduct.setBrand(productDTO.getBrand());
        existingProduct.setProductCategory(productDTO.getProductCategory());
        existingProduct.setPrice(productDTO.getPrice());
        existingProduct.setQuantity(productDTO.getQuantity());
        existingProduct.setImage(productDTO.getImage());
        existingProduct.setDescription(productDTO.getDescription());

        Product updatedProduct = productRepository.save(existingProduct);
        return ProductMapper.toDTO(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDTO> getProductsByCategory(Product.ProductCategory category) {
        List<Product> products = productRepository.findByProductCategory(category);
        return products.stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CannabisProductDTO createCannabisProduct(CannabisProductDTO cannabisProductDTO) {
        CannabisProduct cannabisProduct = CannabisProductMapper.toEntity(cannabisProductDTO);
        CannabisProduct savedProduct = productRepository.save(cannabisProduct);
        return CannabisProductMapper.toDTO(savedProduct);
    }

    @Override
    public List<CannabisProductDTO> getAllCannabisProducts() {
        List<Product> products = productRepository.findAll();
        List<CannabisProduct> cannabisProducts = products.stream()
                .filter(CannabisProduct.class::isInstance)
                .map(CannabisProduct.class::cast)
                .collect(Collectors.toList());

        return cannabisProducts.stream()
                .map(CannabisProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CannabisProductDTO> createCannabisProducts(List<CannabisProductDTO> cannabisProductDTOs) {
        List<CannabisProduct> cannabisProducts = cannabisProductDTOs.stream()
                .map(CannabisProductMapper::toEntity)
                .collect(Collectors.toList());

        List<CannabisProduct> savedCannabisProducts = productRepository.saveAll(cannabisProducts);

        return savedCannabisProducts.stream()
                .map(CannabisProductMapper::toDTO)
                .collect(Collectors.toList());
    }

}