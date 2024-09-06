package com.sasa.backend.dto.order;

import com.sasa.backend.dto.product.ProductDTO;
import com.sasa.backend.dto.user.AddressDTO;
import com.sasa.backend.dto.user.UserDTO;
import com.sasa.backend.entity.order.Order.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {

    private Long id;
    private Long userId;
    private OrderStatus status; // Use existing enum
    private LocalDateTime orderReceivedTimestamp;
    private LocalDateTime expectedDeliveryTimestamp;
    private AddressDTO shippingAddress;
    private AddressDTO billingAddress;
    private List<ProductDTO> productSummary;
    private Map<String, Double> priceSummary;
    private UserDTO user;
}