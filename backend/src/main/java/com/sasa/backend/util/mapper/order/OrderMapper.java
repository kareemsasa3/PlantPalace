package com.sasa.backend.util.mapper.order;

import com.sasa.backend.dto.order.OrderDTO;
import com.sasa.backend.entity.order.Order;
import com.sasa.backend.entity.product.Product;

import java.util.stream.Collectors;

public class OrderMapper {

    private OrderMapper() {
        throw new IllegalStateException();
    }

    public static OrderDTO toDTO(Order order) {
        if (order == null) {
            return null;
        }

        return OrderDTO.builder()
                .id(order.getId())
                .userId(order.getUser() != null ? order.getUser().getId() : null)
                .status(order.getStatus() != null ? order.getStatus().name() : null)
                .orderReceivedTimestamp(order.getOrderReceivedTimestamp())
                .expectedDeliveryTimestamp(order.getExpectedDeliveryTimestamp())
                .shippingAddressId(order.getShippingAddress() != null ? order.getShippingAddress().getId() : null)
                .billingAddressId(order.getBillingAddress() != null ? order.getBillingAddress().getId() : null)
                .priceSummary(order.getPriceSummary())
                .productIds(order.getProductSummary() != null
                        ? order.getProductSummary().stream().map(Product::getId).collect(Collectors.toList())
                        : null)
                .build();
    }

    public static Order toEntity(OrderDTO dto) {
        if (dto == null) {
            return null;
        }
        return new Order(dto);
    }
}