package com.sasa.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sasa.backend.constant.Constants;
import com.sasa.backend.dto.AddressDTO;
import com.sasa.backend.entity.Address;
import com.sasa.backend.entity.AddressType;
import com.sasa.backend.exception.InvalidEnumValueException;
import com.sasa.backend.exception.ResourceNotFoundException;
import com.sasa.backend.mapper.AddressMapper;
import com.sasa.backend.repository.AddressRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AddressDTO> getAllAddresses() {
        return addressRepository.findAll().stream()
                .map(AddressMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public AddressDTO getAddressById(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Constants.ADDRESS_NOT_FOUND_MESSAGE + id));
        return AddressMapper.toDTO(address);
    }

    @Override
    @Transactional
    public AddressDTO createAddress(AddressDTO addressDTO) {
        Address address = AddressMapper.toEntity(addressDTO);
        Address createdAddress = addressRepository.save(address);
        return AddressMapper.toDTO(createdAddress);
    }

    @Override
    @Transactional
    public AddressDTO updateAddress(Long id, AddressDTO addressDTO) {
        Address existingAddress = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Constants.ADDRESS_NOT_FOUND_MESSAGE + id));
        
        if (addressDTO.getStreetAddress() != null) {
            existingAddress.setStreetAddress(addressDTO.getStreetAddress());
        }
        if (addressDTO.getAddressType() != null) {
            existingAddress.setAddressType(convertStringToEnum(AddressType.class, addressDTO.getAddressType()));
        }
        if (addressDTO.getCity() != null) {
            existingAddress.setCity(addressDTO.getCity());
        }
        if (addressDTO.getState() != null) {
            existingAddress.setState(addressDTO.getState());
        }
        if (addressDTO.getPostalCode() != null) {
            existingAddress.setPostalCode(addressDTO.getPostalCode());
        }
        if (addressDTO.getFirstName() != null) {
            existingAddress.setFirstName(addressDTO.getFirstName());
        }
        if (addressDTO.getLastName() != null) {
            existingAddress.setLastName(addressDTO.getLastName());
        }

        Address updatedAddress = addressRepository.save(existingAddress);
        return AddressMapper.toDTO(updatedAddress);
    }

    @Override
    @Transactional
    public void deleteAddress(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Constants.ADDRESS_NOT_FOUND_MESSAGE + id));
        addressRepository.delete(address);
    }

    private <T extends Enum<T>> T convertStringToEnum(Class<T> enumType, String type) {
        try {
            return Enum.valueOf(enumType, type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidEnumValueException(
                String.format(Constants.INVALID_ENUM_VALUE_MESSAGE, type, enumType.getSimpleName()));
        }
    }
}
