package com.sasa.backend.mapper;

import java.util.Collections;

import com.sasa.backend.dto.ProductDTO;
import com.sasa.backend.entity.Category;
import com.sasa.backend.entity.Product;
import com.sasa.backend.entity.Type;
import com.sasa.backend.exception.InvalidEnumValueException;

public final class ProductMapper {

    private ProductMapper() {
        throw new AssertionError("Cannot instantiate utility class");
    }

    public static ProductDTO toDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setCategory(product.getCategory().name());
        dto.setName(product.getName());
        dto.setBrand(product.getBrand());
        dto.setType(product.getType().name());
        dto.setThc(product.getThc());
        dto.setAmount(product.getAmount());
        dto.setPrice(product.getPrice());
        dto.setQuantity(product.getQuantity());
        dto.setImage(product.getImage());
        dto.setDescription(product.getDescription());
        dto.setEffects(product.getEffects());
        dto.setTerpenes(product.getTerpenes() != null ? product.getTerpenes() : Collections.emptyMap());
        return dto;
    }

    public static Product toEntity(ProductDTO dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setCategory(convertStringToEnum(Category.class, dto.getCategory()));
        product.setName(dto.getName());
        product.setBrand(dto.getBrand());
        product.setType(convertStringToEnum(Type.class, dto.getType()));
        product.setThc(dto.getThc());
        product.setAmount(dto.getAmount());
        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());
        product.setImage(dto.getImage());
        product.setDescription(dto.getDescription());
        product.setEffects(dto.getEffects());
        product.setTerpenes(dto.getTerpenes() != null ? dto.getTerpenes() : Collections.emptyMap());
        return product;
    }

    private static <T extends Enum<T>> T convertStringToEnum(Class<T> enumType, String type) {
        if (type == null) {
            throw new InvalidEnumValueException("Type cannot be null for enum: " + enumType.getSimpleName());
        }
        try {
            return Enum.valueOf(enumType, type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidEnumValueException("Invalid value: " + type + " for enum: " + enumType.getSimpleName());
        }
    }
}
