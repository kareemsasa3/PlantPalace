package com.sasa.backend.entity.product.cannabis;

import java.util.Map;

import com.sasa.backend.dto.product.cannabis.CannabisProductDTO;
import com.sasa.backend.entity.product.Product;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MapKeyColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class CannabisProduct extends Product {
    
    public enum CannabisCategory {
        FLOWER,
        CONCENTRATES,
        EDIBLES,
        PRE_ROLLS,
        VAPORIZERS,
        TOPICALS,
        TINCTURES,
        ACCESSORIES
    }

    public enum CannabisType {
        INDICA,
        SATIVA,
        HYBRID
    }

    public CannabisProduct(CannabisProductDTO dto) {
        super(dto.getId(),
            dto.getName(),
            dto.getBrand(),
            ProductCategory.CANNABIS,
            dto.getPrice(),
            dto.getQuantity(),
            dto.getImage(),
            dto.getDescription());

        this.type = CannabisType.valueOf(dto.getType());
        this.category = CannabisCategory.valueOf(dto.getCategory());
        this.thcContent = dto.getThcContent();
        this.cbdContent = dto.getCbdContent();
        this.terpenes = dto.getTerpenes();
        this.weight = dto.getWeight();
        this.strain = dto.getStrain();
        this.effects = dto.getEffects();
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CannabisType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CannabisCategory category;

    private Double thcContent;

    private Double cbdContent;

    @ElementCollection
    @MapKeyColumn(name = "terpene_name")
    @Column(name = "terpene_value")
    private Map<String, Double> terpenes;

    private Double weight;

    private String strain;

    private String effects;
}
