package com.sasa.backend.util.mapper.product;

import com.sasa.backend.dto.product.ProductDTO;
import com.sasa.backend.entity.product.Product;
import com.sasa.backend.entity.product.cannabis.CannabisProduct;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

    private ProductMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static ProductDTO toProductDTO(Product product) {
        if (product == null) {
            return null;
        }
        if (product instanceof CannabisProduct) {
            return new ProductDTO(product);
        }
        throw new IllegalArgumentException("Unsupported product type: " + product.getClass());
    }

    public static List<ProductDTO> toProductDTOList(List<Product> products) {
        if (products == null) {
            return Collections.emptyList();
        }
        return products.stream()
                       .map(ProductMapper::toProductDTO)
                       .collect(Collectors.toList());
    }

    public static Product toProduct(ProductDTO productDTO) {
        if (productDTO == null) {
            return null;
        }
        if ("CANNABIS".equals(productDTO.getProductCategory())) {
            return mapToCannabisProduct(productDTO);
        }
        throw new IllegalArgumentException("Unsupported product category: " + productDTO.getProductCategory());
    }

    public static List<Product> toProductList(List<ProductDTO> productDTOs) {
        if (productDTOs == null) {
            return Collections.emptyList();
        }
        return productDTOs.stream()
                          .map(ProductMapper::toProduct)
                          .collect(Collectors.toList());
    }

    private static CannabisProduct mapToCannabisProduct(ProductDTO productDTO) {
        if (productDTO == null) {
            return null;
        }
        return CannabisProduct.builder()
                .id(productDTO.getId())
                .name(productDTO.getName())
                .brand(productDTO.getBrand())
                .productCategory(Product.ProductCategory.CANNABIS)
                .price(productDTO.getPrice())
                .quantity(productDTO.getQuantity())
                .image(productDTO.getImage())
                .description(productDTO.getDescription())
                .build();
    }
}