package com.sasa.backend.service.address;

import java.util.List;

import com.sasa.backend.dto.address.AddressDTO;

public interface AddressService {
    
    List<AddressDTO> getAllAddresses();

    AddressDTO getAddressById(Long id);

    List<AddressDTO> getAddressesByUserId(Long userId);

    AddressDTO createAddress(AddressDTO addressDTO);

    AddressDTO updateAddress(Long id, AddressDTO addressDTO);

    void deleteAddress(Long id);
}
