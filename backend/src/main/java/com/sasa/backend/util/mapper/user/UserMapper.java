package com.sasa.backend.util.mapper.user;

import com.sasa.backend.dto.user.UserDTO;
import com.sasa.backend.entity.address.Address;
import com.sasa.backend.entity.user.User;
import com.sasa.backend.repository.address.AddressRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    private UserMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }
        List<Long> addressIds = user.getAddresses().stream()
            .map(Address::getId)
            .collect(Collectors.toList());
        UserDTO userDTO = new UserDTO(user);
        userDTO.setAddressIds(addressIds);
        return userDTO;
    }

    public static User toEntity(UserDTO userDTO, AddressRepository addressRepository) {
        if (userDTO == null) {
            return null;
        }
        User user = new User(userDTO);
        if (userDTO.getAddressIds() != null) {
            List<Address> addresses = addressRepository.findAllById(userDTO.getAddressIds());
            user.setAddresses(addresses.isEmpty() ? Collections.emptyList() : addresses);
        }
        return user;
    }
}