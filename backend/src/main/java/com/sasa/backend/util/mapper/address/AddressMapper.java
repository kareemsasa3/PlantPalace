package com.sasa.backend.util.mapper.address;

import com.sasa.backend.dto.address.AddressDTO;
import com.sasa.backend.entity.address.Address;

public class AddressMapper {

    private AddressMapper() {
        throw new IllegalStateException();
    }

    public static AddressDTO toDTO(Address address) {
        if (address == null) {
            return null;
        }
        return new AddressDTO(address);
    }

    public static Address toEntity(AddressDTO addressDTO) {
        if (addressDTO == null) {
            return null;
        }
        return new Address(addressDTO);
    }
}