package com.sasa.backend.dto.product;

import com.sasa.backend.entity.product.Product.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
    private Long id;
    private String name;
    private String brand;
    private ProductCategory productCategory;
    private Double price;
    private Integer quantity;
    private String image;
    private String description;
}