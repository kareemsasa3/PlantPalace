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
    public List<ProductDTO> searchProducts(String name, String category, String type, Double minPrice, Double maxPrice) {
        List<Product> products = findProducts(name, category, type, minPrice, maxPrice);
        return products.stream()
                    .map(ProductMapper::toDTO)
                    .collect(Collectors.toList());
    }

    private List<Product> findProducts(String name, String category, String type, Double minPrice, Double maxPrice) {
        Category cat = convertToEnum(category, Category.class);
        Type t = convertToEnum(type, Type.class);

        if (name != null) {
            if (cat != null && t != null) {
                return productRepository.findByNameContainingIgnoreCaseAndCategoryAndType(name, cat, t);
            } else if (cat != null) {
                return productRepository.findByNameContainingIgnoreCaseAndCategory(name, cat);
            } else if (t != null) {
                return productRepository.findByNameContainingIgnoreCaseAndType(name, t);
            } else {
                return productRepository.findByNameContainingIgnoreCase(name);
            }
        } else if (cat != null && t != null) {
            return productRepository.findByCategoryAndType(cat, t);
        } else if (cat != null) {
            return productRepository.findByCategory(cat);
        } else if (t != null) {
            return productRepository.findByType(t);
        } else if (minPrice != null && maxPrice != null) {
            return productRepository.findByPriceBetween(minPrice, maxPrice);
        } else {
            return productRepository.findAll();
        }
    }

    private <E extends Enum<E>> E convertToEnum(String value, Class<E> enumClass) {
        if (value != null) {
            try {
                return Enum.valueOf(enumClass, value.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid value for enum " + enumClass.getSimpleName() + ": " + value);
            }
        }
        return null;
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
