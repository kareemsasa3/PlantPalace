package com.sasa.backend.dto.order;

import com.sasa.backend.entity.order.Order;
import com.sasa.backend.entity.product.Product;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "User ID cannot be null")
    private Long userId;

    @NotBlank(message = "Status cannot be blank")
    private String status;

    @NotNull(message = "Order received timestamp cannot be null")
    private LocalDateTime orderReceivedTimestamp;

    @Future(message = "Expected delivery timestamp must be in the future")
    private LocalDateTime expectedDeliveryTimestamp;

    @NotNull(message = "Shipping address ID cannot be null")
    private Long shippingAddressId;

    @NotNull(message = "Billing address ID cannot be null")
    private Long billingAddressId;

    @NotEmpty(message = "Product IDs cannot be empty")
    private List<Long> productIds;

    private Map<String, Double> priceSummary;
}