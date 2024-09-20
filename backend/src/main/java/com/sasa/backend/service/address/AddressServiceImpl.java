package com.sasa.backend.service.address;

import com.sasa.backend.constant.Constants;
import com.sasa.backend.dto.address.AddressDTO;
import com.sasa.backend.entity.address.Address;
import com.sasa.backend.entity.user.User;
import com.sasa.backend.exception.AddressNotFoundException;
import com.sasa.backend.service.user.UserService;
import com.sasa.backend.repository.address.AddressRepository;
import com.sasa.backend.util.mapper.address.AddressMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final UserService userService;

    @Override
    @Transactional(readOnly = true)
    public AddressDTO getAddressById(Long id) {
        return addressRepository.findById(id)
                .map(AddressMapper::toDTO)
                .orElseThrow(() -> new AddressNotFoundException(Constants.ADDRESS_NOT_FOUND_MESSAGE + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<AddressDTO> getAddressesByUserId(Long userId) {
        User user = userService.findById(userId);  // Use UserService to fetch user

        return addressRepository.findByUser(user).stream()
                .map(AddressMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AddressDTO createAddress(AddressDTO addressDTO) {
        User user;
        Address address = AddressMapper.toEntity(addressDTO);
        if (addressDTO.getUserId() != null) {
            user = userService.findById(addressDTO.getUserId());  // Use UserService to fetch user
            user.getAddresses().add(address);
            address.setUser(user);
        } else {
            address.setUser(null);
        }
        Address savedAddress = addressRepository.save(address);
        return AddressMapper.toDTO(savedAddress);
    }

    @Override
    @Transactional
    public AddressDTO updateAddress(Long id, AddressDTO addressDTO) {
        // Find existing address or throw exception if not found
        Address existingAddress = addressRepository.findById(id)
                .orElseThrow(() -> new AddressNotFoundException("Address not found with id: " + id));

        // Update fields with values from AddressDTO
        existingAddress.setStreetAddress(addressDTO.getStreetAddress());
        
        // Convert string to Address.AddressType enum
        Address.AddressType addressType = Address.AddressType.valueOf(addressDTO.getAddressType());
        existingAddress.setAddressType(addressType);

        existingAddress.setCity(addressDTO.getCity());
        existingAddress.setState(addressDTO.getState());
        existingAddress.setPostalCode(addressDTO.getPostalCode());
        existingAddress.setFirstName(addressDTO.getFirstName());
        existingAddress.setLastName(addressDTO.getLastName());
        existingAddress.setEmailAddress(addressDTO.getEmailAddress());

        // Save the updated address entity
        Address updatedAddress = addressRepository.save(existingAddress);
        
        // Convert to DTO and return
        return AddressMapper.toDTO(updatedAddress);
    }

    @Override
    @Transactional
    public void deleteAddress(Long id) {
        if (addressRepository.existsById(id)) {
            addressRepository.deleteById(id);
        } else {
            throw new AddressNotFoundException("Address not found with id: " + id);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<AddressDTO> getAllAddresses() {
        return addressRepository.findAll().stream()
                .map(AddressMapper::toDTO)
                .collect(Collectors.toList());
    }
}