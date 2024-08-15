package com.sasa.backend.mapper;

import com.sasa.backend.constant.Constants;
import com.sasa.backend.dto.OrderDTO;
import com.sasa.backend.entity.Order;
import com.sasa.backend.entity.OrderStatus;
import com.sasa.backend.exception.InvalidEnumValueException;
import com.sasa.backend.entity.User; // Import User entity if needed

import java.util.stream.Collectors;

public final class OrderMapper {

    private OrderMapper() {
        throw new AssertionError("Cannot instantiate utility class");
    }

    public static OrderDTO toDTO(Order order) {
        if (order == null) {
            return null; // Handle null orders gracefully
        }

        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setStatus(order.getStatus() != null ? order.getStatus().name() : null);
        dto.setOrderReceivedTimestamp(order.getOrderReceivedTimestamp());
        dto.setExpectedDeliveryTimestamp(order.getExpectedDeliveryTimestamp());
        dto.setBillingAddress(AddressMapper.toDTO(order.getBillingAddress()));
        dto.setShippingAddress(AddressMapper.toDTO(order.getShippingAddress()));
        dto.setProductSummary(order.getProductSummary().stream()
                                .map(ProductMapper::toDTO)
                                .collect(Collectors.toList()));
        dto.setPriceSummary(order.getPriceSummary());

        // Map user field to userId
        dto.setUserId(order.getUser() != null ? order.getUser().getId() : null);
        
        return dto;
    }

    public static Order toEntity(OrderDTO dto) {
        if (dto == null) {
            return null; // Handle null DTOs gracefully
        }

        Order order = new Order();
        order.setId(dto.getId());
        order.setStatus(convertStringToEnum(OrderStatus.class, dto.getStatus()));
        order.setOrderReceivedTimestamp(dto.getOrderReceivedTimestamp());
        order.setExpectedDeliveryTimestamp(dto.getExpectedDeliveryTimestamp());
        order.setBillingAddress(AddressMapper.toEntity(dto.getBillingAddress()));
        order.setShippingAddress(AddressMapper.toEntity(dto.getShippingAddress()));
        order.setProductSummary(dto.getProductSummary().stream()
                                    .map(ProductMapper::toEntity)
                                    .collect(Collectors.toList()));
        order.setPriceSummary(dto.getPriceSummary());

        // Map userId to User entity if needed
        if (dto.getUserId() != null) {
            User user = new User(); // You may need to fetch the User from the database or service
            user.setId(dto.getUserId());
            order.setUser(user);
        } else {
            order.setUser(null);
        }
        
        return order;
    }

    public static <T extends Enum<T>> T convertStringToEnum(Class<T> enumType, String type) {
        if (type == null) {
            throw new IllegalArgumentException("Enum value cannot be null"); // Handle null type
        }
        try {
            return Enum.valueOf(enumType, type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidEnumValueException(
                String.format(Constants.INVALID_ENUM_VALUE_MESSAGE));
        }
    }
}
