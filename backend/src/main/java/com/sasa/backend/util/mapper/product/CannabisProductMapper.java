package com.sasa.backend.util.mapper.product;

import com.sasa.backend.dto.product.CannabisProductDTO;
import com.sasa.backend.entity.product.CannabisProduct;
import org.springframework.stereotype.Component;

@Component
public class CannabisProductMapper {

    private CannabisProductMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static CannabisProductDTO toDTO(CannabisProduct cannabisProduct) {
        if (cannabisProduct == null) {
            return null;
        }

        CannabisProductDTO dto = new CannabisProductDTO();
        dto.setId(cannabisProduct.getId());
        dto.setName(cannabisProduct.getName());
        dto.setBrand(cannabisProduct.getBrand());
        dto.setProductCategory(cannabisProduct.getProductCategory());
        dto.setPrice(cannabisProduct.getPrice());
        dto.setQuantity(cannabisProduct.getQuantity());
        dto.setImage(cannabisProduct.getImage());
        dto.setDescription(cannabisProduct.getDescription());
        dto.setCannabisType(cannabisProduct.getCannabisType());
        dto.setCannabisCategory(cannabisProduct.getCannabisCategory());
        dto.setThcContent(cannabisProduct.getThcContent());
        dto.setCbdContent(cannabisProduct.getCbdContent());
        dto.setTerpenes(cannabisProduct.getTerpenes());
        dto.setWeight(cannabisProduct.getWeight());
        dto.setStrain(cannabisProduct.getStrain());
        dto.setEffects(cannabisProduct.getEffects());

        return dto;
    }

    public static CannabisProduct toEntity(CannabisProductDTO dto) {
        if (dto == null) {
            return null;
        }

        CannabisProduct cannabisProduct = new CannabisProduct();
        cannabisProduct.setId(dto.getId());
        cannabisProduct.setName(dto.getName());
        cannabisProduct.setBrand(dto.getBrand());
        cannabisProduct.setProductCategory(dto.getProductCategory());
        cannabisProduct.setPrice(dto.getPrice());
        cannabisProduct.setQuantity(dto.getQuantity());
        cannabisProduct.setImage(dto.getImage());
        cannabisProduct.setDescription(dto.getDescription());
        cannabisProduct.setCannabisType(dto.getCannabisType());
        cannabisProduct.setCannabisCategory(dto.getCannabisCategory());
        cannabisProduct.setThcContent(dto.getThcContent());
        cannabisProduct.setCbdContent(dto.getCbdContent());
        cannabisProduct.setTerpenes(dto.getTerpenes());
        cannabisProduct.setWeight(dto.getWeight());
        cannabisProduct.setStrain(dto.getStrain());
        cannabisProduct.setEffects(dto.getEffects());

        return cannabisProduct;
    }
}