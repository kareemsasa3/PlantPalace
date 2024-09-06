package com.sasa.backend.util.mapper.user;

import com.sasa.backend.dto.user.UserDTO;
import com.sasa.backend.entity.user.User;
import com.sasa.backend.util.mapper.order.OrderMapper;
import com.sasa.backend.entity.order.Order;
import com.sasa.backend.entity.user.Address;
import com.sasa.backend.entity.user.Role;
import com.sasa.backend.dto.order.OrderDTO;
import com.sasa.backend.dto.user.AddressDTO;
import com.sasa.backend.dto.user.RoleDTO;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    private UserMapper() {
        throw new IllegalStateException("Util class");
    }

    public static UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }

        List<OrderDTO> orderDTOs = user.getOrderHistory().stream()
                .map(OrderMapper::toDTO) // Assuming OrderMapper exists
                .collect(Collectors.toList());

        List<RoleDTO> roleDTOs = user.getRoles().stream()
                .map(RoleMapper::toDTO) // Assuming RoleMapper exists
                .collect(Collectors.toList());

        List<AddressDTO> addressDTOs = user.getAddresses().stream()
                .map(AddressMapper::toDTO) // Assuming AddressMapper exists
                .collect(Collectors.toList());

        return new UserDTO(
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            user.getFirstName(),
            user.getLastName(),
            orderDTOs,
            roleDTOs,
            addressDTOs
        );
    }

    public static User toEntity(UserDTO dto) {
        if (dto == null) {
            return null;
        }

        List<Order> orders = dto.getOrderHistory().stream()
                .map(OrderMapper::toEntity) // Assuming OrderMapper exists
                .collect(Collectors.toList());

        List<Role> roles = dto.getRoles().stream()
                .map(RoleMapper::toEntity) // Assuming RoleMapper exists
                .collect(Collectors.toList());

        List<Address> addresses = dto.getAddresses().stream()
                .map(AddressMapper::toEntity) // Assuming AddressMapper exists
                .collect(Collectors.toList());

        return new User(
            dto.getId(),
            dto.getUsername(),
            dto.getEmail(),
            null,
            dto.getFirstName(),
            dto.getLastName(),
            orders,
            roles,
            addresses
        );
    }
}