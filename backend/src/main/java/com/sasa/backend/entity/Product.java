package com.sasa.backend.entity;

import java.util.Map;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String name;

    private String brand;

    @Enumerated(EnumType.STRING)
    private Type type;

    private Double thc;

    private Double amount;

    private Double price;

    private Integer quantity;

    private String image;

    @Lob
    private String description;

    @Lob
    private String effects;

    @ElementCollection
    private Map<String, Double> terpenes;
}
