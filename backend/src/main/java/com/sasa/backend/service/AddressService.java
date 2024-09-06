package com.sasa.backend.service;

import java.util.List;

import com.sasa.backend.dto.user.AddressDTO;

public interface AddressService {
    
    List<AddressDTO> getAllAddresses();

    AddressDTO getAddressById(Long id);

    AddressDTO createAddress(AddressDTO addressDTO);

    AddressDTO updateAddress(Long id, AddressDTO addressDTO);

    void deleteAddress(Long id);
}
