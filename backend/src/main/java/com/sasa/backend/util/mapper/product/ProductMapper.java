package com.sasa.backend.util.mapper.product;

import com.sasa.backend.dto.product.ProductDTO;
import com.sasa.backend.entity.product.CannabisProduct;
import com.sasa.backend.entity.product.Product;

public class ProductMapper {

    // Convert Product entity to ProductDTO
    public ProductDTO toDTO(Product product) {
        if (product == null) {
            return null;
        }

        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .brand(product.getBrand())
                .productCategory(product.getProductCategory())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .image(product.getImage())
                .description(product.getDescription())
                .build();
    }

    // Convert ProductDTO to Product entity
    public Product toEntity(ProductDTO productDTO) {
        if (productDTO == null) {
            return null;
        }

        switch (productDTO.getProductCategory()) {
            case CANNABIS:
                CannabisProduct cannabisProduct = new CannabisProduct();
                cannabisProduct.setId(productDTO.getId());
                cannabisProduct.setName(productDTO.getName());
                cannabisProduct.setBrand(productDTO.getBrand());
                cannabisProduct.setProductCategory(productDTO.getProductCategory());
                cannabisProduct.setPrice(productDTO.getPrice());
                cannabisProduct.setQuantity(productDTO.getQuantity());
                cannabisProduct.setImage(productDTO.getImage());
                cannabisProduct.setDescription(productDTO.getDescription());
                // set other CannabisProduct-specific fields here
                return cannabisProduct;
            // Handle other product types (e.g., CLOTHING, ELECTRONICS, etc.)
            case CLOTHING:
            case ELECTRONICS:
            case GAMES:
            case TOYS:
            default:
                throw new IllegalArgumentException("Unknown product category: " + productDTO.getProductCategory());
        }
    }
}