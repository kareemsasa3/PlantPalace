package com.sasa.backend.dto.product;

import com.sasa.backend.entity.product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.brand = product.getBrand();
        this.productCategory = product.getProductCategory().name();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
        this.image = product.getImage();
        this.description = product.getDescription();
    }

    private Long id;

    private String name;

    private String brand;

    private String productCategory;

    private Double price;

    private Integer quantity;

    private String image;
    
    private String description;
}