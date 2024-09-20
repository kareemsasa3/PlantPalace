package com.sasa.backend.service.order;

import com.sasa.backend.dto.order.OrderDTO;
import com.sasa.backend.entity.order.Order;
import com.sasa.backend.entity.user.User;
import com.sasa.backend.exception.OrderNotFoundException;
import com.sasa.backend.exception.UserNotFoundException;
import com.sasa.backend.repository.order.OrderRepository;
import com.sasa.backend.repository.user.UserRepository;
import com.sasa.backend.util.mapper.order.OrderMapper;
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
    public void deleteOrder(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
        } else {
            throw new OrderNotFoundException("Order not found with id: " + id);
        }
    }
}