package com.sasa.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sasa.backend.constant.Constants;
import com.sasa.backend.dto.AddressDTO;
import com.sasa.backend.entity.Address;
import com.sasa.backend.entity.AddressType;
import com.sasa.backend.entity.User;
import com.sasa.backend.exception.InvalidEnumValueException;
import com.sasa.backend.exception.ResourceNotFoundException;
import com.sasa.backend.mapper.AddressMapper;
import com.sasa.backend.repository.AddressRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final UserService userService; // Inject UserService

    public AddressServiceImpl(AddressRepository addressRepository, UserService userService) {
        this.addressRepository = addressRepository;
        this.userService = userService; // Initialize UserService
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
        if (addressDTO.getUserId() != null) {
            User user = userService.findById(addressDTO.getUserId());
            address.setUser(user);
        }
        Address createdAddress = addressRepository.save(address);
        return AddressMapper.toDTO(createdAddress);
    }

    @Override
    @Transactional
    public AddressDTO updateAddress(Long id, AddressDTO addressDTO) {
        // Fetch the existing address entity
        Address existingAddress = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Constants.ADDRESS_NOT_FOUND_MESSAGE + id));

        // Convert the DTO to an entity
        Address updatedAddress = AddressMapper.toEntity(addressDTO);

        // Copy the updated fields from the DTO to the existing entity
        if (addressDTO.getStreetAddress() != null) {
            existingAddress.setStreetAddress(updatedAddress.getStreetAddress());
        }
        if (addressDTO.getAddressType() != null) {
            existingAddress.setAddressType(convertStringToEnum(AddressType.class, addressDTO.getAddressType()));
        }
        if (addressDTO.getCity() != null) {
            existingAddress.setCity(updatedAddress.getCity());
        }
        if (addressDTO.getState() != null) {
            existingAddress.setState(updatedAddress.getState());
        }
        if (addressDTO.getPostalCode() != null) {
            existingAddress.setPostalCode(updatedAddress.getPostalCode());
        }
        if (addressDTO.getFirstName() != null) {
            existingAddress.setFirstName(updatedAddress.getFirstName());
        }
        if (addressDTO.getLastName() != null) {
            existingAddress.setLastName(updatedAddress.getLastName());
        }
        if (addressDTO.getEmailAddress() != null) {
            existingAddress.setEmailAddress(updatedAddress.getEmailAddress());
        }

        // Set the User if userId is provided
        if (addressDTO.getUserId() != null) {
            User user = userService.findById(addressDTO.getUserId());
            existingAddress.setUser(user);
        }

        // Save the updated address
        Address savedAddress = addressRepository.save(existingAddress);

        // Convert the saved entity back to DTO
        return AddressMapper.toDTO(savedAddress);
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
