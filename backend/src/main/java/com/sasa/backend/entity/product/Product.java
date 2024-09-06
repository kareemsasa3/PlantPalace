package com.sasa.backend.entity.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Product {
    
    public enum ProductCategory {
        CANNABIS, CLOTHING, ELECTRONICS, GAMES, TOYS
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String brand;

    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;

    private Double price;

    private Integer quantity;

    private String image;

    @Lob
    private String description;
}
