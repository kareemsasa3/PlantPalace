package com.sasa.medmanage.mapper;

import com.sasa.medmanage.constant.Constants;
import com.sasa.medmanage.dto.AddressDTO;
import com.sasa.medmanage.entity.Address;
import com.sasa.medmanage.entity.AddressType;
import com.sasa.medmanage.exception.InvalidEnumValueException;

public final class AddressMapper {

    private AddressMapper() {
        throw new AssertionError("Cannot instantiate utility class");
    }

    public static AddressDTO toDTO(Address address) {
        if (address == null) {
            return null; // Handle null addresses gracefully
        }
        
        AddressDTO dto = new AddressDTO();
        dto.setId(address.getId());
        dto.setStreetAddress(address.getStreetAddress());
        dto.setAddressType(address.getAddressType() != null ? address.getAddressType().name() : null);
        dto.setCity(address.getCity());
        dto.setState(address.getState());
        dto.setPostalCode(address.getPostalCode());
        return dto;
    }

    public static Address toEntity(AddressDTO dto) {
        if (dto == null) {
            return null; // Handle null DTOs gracefully
        }
        
        Address address = new Address();
        address.setId(dto.getId());
        address.setStreetAddress(dto.getStreetAddress());
        address.setAddressType(convertStringToEnum(AddressType.class, dto.getAddressType()));
        address.setCity(dto.getCity());
        address.setState(dto.getState());
        address.setPostalCode(dto.getPostalCode());
        return address;
    }

    private static <T extends Enum<T>> T convertStringToEnum(Class<T> enumType, String type) {
        if (type == null) {
            throw new IllegalArgumentException("Enum value cannot be null"); // Handle null type
        }
        try {
            return Enum.valueOf(enumType, type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidEnumValueException(
                String.format(Constants.INVALID_ENUM_VALUE_MESSAGE));
        }
    }
}
