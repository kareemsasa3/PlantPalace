package com.sasa.backend.util.mapper.user;

import com.sasa.backend.dto.user.AddressDTO;
import com.sasa.backend.entity.user.Address;

public class AddressMapper {

    private AddressMapper () {
        // LEFT BLANK ON PURPOSE
    }

    // Convert AddressDTO to Address entity
    public static Address toEntity(AddressDTO addressDTO) {
        if (addressDTO == null) {
            return null;
        }
        Address address = new Address();
        address.setId(addressDTO.getId());
        address.setStreetAddress(addressDTO.getStreetAddress());
        address.setAddressType(addressDTO.getAddressType());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setPostalCode(addressDTO.getPostalCode());
        address.setFirstName(addressDTO.getFirstName());
        address.setLastName(addressDTO.getLastName());
        address.setEmailAddress(addressDTO.getEmailAddress());
        // user can be set later if needed or managed by other parts of the application
        return address;
    }

    // Convert Address entity to AddressDTO
    public static AddressDTO toDTO(Address address) {
        if (address == null) {
            return null;
        }
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(address.getId());
        addressDTO.setStreetAddress(address.getStreetAddress());
        addressDTO.setAddressType(address.getAddressType());
        addressDTO.setCity(address.getCity());
        addressDTO.setState(address.getState());
        addressDTO.setPostalCode(address.getPostalCode());
        addressDTO.setFirstName(address.getFirstName());
        addressDTO.setLastName(address.getLastName());
        addressDTO.setEmailAddress(address.getEmailAddress());
        return addressDTO;
    }
}