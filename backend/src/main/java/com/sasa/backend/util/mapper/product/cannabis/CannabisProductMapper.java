package com.sasa.backend.util.mapper.product.cannabis;

import java.util.List;
import java.util.stream.Collectors;

import com.sasa.backend.dto.product.cannabis.CannabisProductDTO;
import com.sasa.backend.entity.product.cannabis.CannabisProduct;

public class CannabisProductMapper {

    private CannabisProductMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static CannabisProductDTO toDTO(CannabisProduct cannabisProduct) {
        if (cannabisProduct == null) {
            return null;
        }
        return new CannabisProductDTO(cannabisProduct);
    }

    public static CannabisProduct toProduct(CannabisProductDTO cannabisProductDTO) {
        if (cannabisProductDTO == null) {
            return null;
        }
        return new CannabisProduct(cannabisProductDTO);
    }

    public static List<CannabisProductDTO> toDTOList(List<CannabisProduct> cannabisProducts) {
        return cannabisProducts.stream()
            .map(CannabisProductMapper::toDTO)
            .collect(Collectors.toList());
    }

    public static List<CannabisProduct> toProductList(List<CannabisProductDTO> cannabisProductDTOs) {
        return cannabisProductDTOs.stream()
            .map(CannabisProductMapper::toProduct)
            .collect(Collectors.toList());
    }
}