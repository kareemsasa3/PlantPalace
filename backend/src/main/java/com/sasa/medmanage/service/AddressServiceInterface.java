package com.sasa.medmanage.service;

import com.sasa.medmanage.dto.AddressDTO;

import java.util.List;

public interface AddressServiceInterface {
    
    List<AddressDTO> getAllAddresses();

    AddressDTO getAddressById(Long id);

    AddressDTO createAddress(AddressDTO addressDTO);

    AddressDTO updateAddress(Long id, AddressDTO addressDTO);

    void deleteAddress(Long id);
}
