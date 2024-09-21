package com.sasa.backend.dto.product;

import com.sasa.backend.entity.product.Product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
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

    @NotBlank(message = "Product name cannot be blank")
    private String name;

    @NotBlank(message = "Brand cannot be blank")
    private String brand;

    @NotBlank(message = "Product category cannot be blank")
    private String productCategory;

    @NotNull(message = "Price cannot be null")
    @Positive(message = "Price must be a positive number")
    private Double price;

    @NotNull(message = "Quantity cannot be null")
    @PositiveOrZero(message = "Quantity must be zero or positive")
    private Integer quantity;

    private String image;
    
    private String description;
}