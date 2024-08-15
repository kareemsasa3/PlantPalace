package com.sasa.backend.dto;

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
    private String status;
    private LocalDateTime orderReceivedTimestamp;
    private LocalDateTime expectedDeliveryTimestamp;
    private AddressDTO shippingAddress;
    private AddressDTO billingAddress;
    private List<ProductDTO> productSummary;
    private Map<String, Double> priceSummary;
    private Long userId;
}
