package com.sasa.backend.service;

import java.util.List;

import com.sasa.backend.dto.order.OrderDTO;

public interface OrderService {
    
    OrderDTO getOrderById(Long id);
    
    List<OrderDTO> getOrdersByUserId(Long userId);

    OrderDTO createOrder(OrderDTO orderDTO);

    void deleteOrder(Long id);
}
