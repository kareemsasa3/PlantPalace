package com.sasa.backend.dto.product.cannabis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Map;

import com.sasa.backend.dto.product.ProductDTO;
import com.sasa.backend.entity.product.cannabis.CannabisProduct;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CannabisProductDTO extends ProductDTO {

    public CannabisProductDTO(CannabisProduct cannabisProduct) {
        super(cannabisProduct);
        this.type = cannabisProduct.getType().name();
        this.category = cannabisProduct.getCategory().name();
        this.thcContent = cannabisProduct.getThcContent();
        this.cbdContent = cannabisProduct.getCbdContent();
        this.terpenes = cannabisProduct.getTerpenes();
        this.weight = cannabisProduct.getWeight();
        this.strain = cannabisProduct.getStrain();
        this.effects = cannabisProduct.getEffects();
    }

    @NotBlank(message = "Type cannot be blank")
    private String type;

    @NotBlank(message = "Category cannot be blank")
    private String category;

    @NotNull(message = "THC content cannot be null")
    @PositiveOrZero(message = "THC content cannot be negative")
    private Double thcContent;

    @NotNull(message = "CBD content cannot be null")
    @PositiveOrZero(message = "CBD content cannot be negative")
    private Double cbdContent;

    @NotNull(message = "Terpenes cannot be null")
    private Map<String, Double> terpenes;

    @NotNull(message = "Weight cannot be null")
    @PositiveOrZero(message = "Weight must be zero or positive")
    private Double weight;

    @NotBlank(message = "Strain cannot be blank")
    private String strain;

    private String effects;
}