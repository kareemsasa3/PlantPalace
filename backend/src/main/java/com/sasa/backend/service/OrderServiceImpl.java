package com.sasa.backend.service;

import com.sasa.backend.dto.OrderDTO;
import com.sasa.backend.entity.Order;
import com.sasa.backend.entity.OrderStatus;
import com.sasa.backend.entity.User;
import com.sasa.backend.exception.OrderNotFoundException;
import com.sasa.backend.exception.UserNotFoundException;
import com.sasa.backend.mapper.AddressMapper;
import com.sasa.backend.mapper.OrderMapper;
import com.sasa.backend.mapper.ProductMapper;
import com.sasa.backend.repository.OrderRepository;
import com.sasa.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public OrderDTO getOrderById(Long id) {
        return orderRepository.findById(id)
            .map(OrderMapper::toDTO)
            .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDTO> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId).stream()
            .map(OrderMapper::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = OrderMapper.toEntity(orderDTO);

        // Fetch the user based on userId from DTO
        Optional<User> user = userRepository.findById(orderDTO.getUserId());
        if (user.isPresent()) {
            order.setUser(user.get());
        } else {
            // Handle user not found (could throw an exception or handle accordingly)
            throw new UserNotFoundException("User not found with id: " + orderDTO.getUserId());
        }

        // Save the order and return the DTO
        Order savedOrder = orderRepository.save(order);
        return OrderMapper.toDTO(savedOrder);
    }

    @Override
    @Transactional
    public OrderDTO updateOrder(Long id, OrderDTO orderDTO) {
        Optional<Order> existingOrder = orderRepository.findById(id);
        if (existingOrder.isPresent()) {
            Order order = existingOrder.get();

            // Update fields from DTO
            order.setStatus(OrderMapper.convertStringToEnum(OrderStatus.class, orderDTO.getStatus()));
            order.setOrderReceivedTimestamp(orderDTO.getOrderReceivedTimestamp());
            order.setExpectedDeliveryTimestamp(orderDTO.getExpectedDeliveryTimestamp());
            order.setBillingAddress(AddressMapper.toEntity(orderDTO.getBillingAddress()));
            order.setShippingAddress(AddressMapper.toEntity(orderDTO.getShippingAddress()));
            order.setProductSummary(orderDTO.getProductSummary().stream()
                .map(ProductMapper::toEntity)
                .collect(Collectors.toList()));
            order.setPriceSummary(orderDTO.getPriceSummary());

            // Save the updated order and return the DTO
            Order updatedOrder = orderRepository.save(order);
            return OrderMapper.toDTO(updatedOrder);
        }
        return null; // Handle order not found case
    }

    @Override
    @Transactional
    public void deleteOrder(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
        } else {
            throw new OrderNotFoundException("Order not found with id: " + id);
        }
    }

}
