package com.sasa.backend.dto.order;

import com.sasa.backend.entity.order.Order;
import com.sasa.backend.entity.product.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {

    public OrderDTO(Order order) {
        this.id = order.getId();
        this.userId = order.getUser() != null ? order.getUser().getId() : null;
        this.status = order.getStatus() != null ? order.getStatus().name() : null;
        this.orderReceivedTimestamp = order.getOrderReceivedTimestamp();
        this.expectedDeliveryTimestamp = order.getExpectedDeliveryTimestamp();
        
        this.shippingAddressId = order.getShippingAddress() != null ? order.getShippingAddress().getId() : null;
        this.billingAddressId = order.getBillingAddress() != null ? order.getBillingAddress().getId() : null;
        
        this.priceSummary = order.getPriceSummary();
        
        this.productIds = order.getProductSummary() != null 
            ? order.getProductSummary().stream().map(Product::getId).collect(Collectors.toList())
            : null;
    }

    private Long id;

    private Long userId;

    private String status;

    private LocalDateTime orderReceivedTimestamp;

    private LocalDateTime expectedDeliveryTimestamp;

    private Long shippingAddressId;

    private Long billingAddressId;

    private List<Long> productIds;

    private Map<String, Double> priceSummary;
}