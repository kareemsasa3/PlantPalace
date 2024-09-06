package com.sasa.backend.entity.product;

import java.util.Map;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
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
    
    public enum CannabisType {
        SATIVA, INDICA, HYBRID
    }

    public enum CannabisCategory {
        FLOWER, CONCENTRATES, EDIBLES,
        PRE_ROLLS, VAPORIZERS, TOPICALS,
        TINCTURES, ACCESSORIES
    }

    @Enumerated
    private CannabisType cannabisType;

    @Enumerated
    private CannabisCategory cannabisCategory;

    private Double thcContent;

    private Double cbdContent;

    @ElementCollection
    private Map<String, Double> terpenes;

    private Double weight;

    private String strain;

    private String effects;
}
