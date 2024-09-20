package com.sasa.backend.entity.order;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.sasa.backend.dto.order.OrderDTO;
import com.sasa.backend.entity.address.Address;
import com.sasa.backend.entity.product.Product;
import com.sasa.backend.entity.user.User;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    public Order(OrderDTO dto) {
        this.id = dto.getId();
        this.status = OrderStatus.valueOf(dto.getStatus());
        this.orderReceivedTimestamp = dto.getOrderReceivedTimestamp();
        this.expectedDeliveryTimestamp = dto.getExpectedDeliveryTimestamp();
        this.priceSummary = dto.getPriceSummary();
    }

    public enum OrderStatus {
        PENDING,
        PROCESSING,
        SHIPPED,
        DELIVERED,
        CANCELED,
        RETURNED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private OrderStatus status;

    private LocalDateTime orderReceivedTimestamp;

    private LocalDateTime expectedDeliveryTimestamp;

    @ManyToOne
    @JoinColumn(name = "shipping_address_id")
    private Address shippingAddress;

    @ManyToOne
    @JoinColumn(name = "billing_address_id")
    private Address billingAddress;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Product> productSummary;

    @ElementCollection
    private Map<String, Double> priceSummary;
}
