package com.sasa.backend.dto.product;

import com.sasa.backend.entity.product.CannabisProduct.CannabisCategory;
import com.sasa.backend.entity.product.CannabisProduct.CannabisType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CannabisProductDTO extends ProductDTO {
    private CannabisType cannabisType;
    private CannabisCategory cannabisCategory;
    private Double thcContent;
    private Double cbdContent;
    private Map<String, Double> terpenes;
    private Double weight;
    private String strain;
    private String effects;
}