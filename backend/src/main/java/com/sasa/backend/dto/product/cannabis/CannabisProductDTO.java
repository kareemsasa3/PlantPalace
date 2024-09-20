package com.sasa.backend.dto.product.cannabis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Map;

import com.sasa.backend.dto.product.ProductDTO;
import com.sasa.backend.entity.product.cannabis.CannabisProduct;

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
    private String type;

    private String category;

    private Double thcContent;

    private Double cbdContent;

    private Map<String, Double> terpenes;

    private Double weight;

    private String strain;

    private String effects;
}