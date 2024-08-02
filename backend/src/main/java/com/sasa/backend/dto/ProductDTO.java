package com.sasa.backend.dto;

import java.util.Map;

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
    private String category;
    private String type;
    private Double thc;
    private Double amount;
    private Double price;
    private Integer quantity;
    private String image;
    private String description;
    private String effects;
    private Map<String, Double> terpenes;
}
