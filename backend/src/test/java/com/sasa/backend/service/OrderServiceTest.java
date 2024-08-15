package com.sasa.backend.service;

import com.sasa.backend.dto.OrderDTO;
import com.sasa.backend.entity.Order;
import com.sasa.backend.entity.OrderStatus;
import com.sasa.backend.exception.OrderNotFoundException;
import com.sasa.backend.mapper.OrderMapper;

import com.sasa.backend.repository.OrderRepository;
import com.sasa.backend.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        // MockitoExtension handles initialization
    }

    @Test
    void testGetOrderById() {
        Order order = new Order();
        order.setId(1L);
        order.setStatus(OrderStatus.PENDING);
        order.setProductSummary(new ArrayList<>());
        OrderDTO orderDTO = OrderMapper.toDTO(order);

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        OrderDTO result = orderService.getOrderById(1L);

        assertNotNull(result);
        assertEquals(orderDTO.getId(), result.getId());
        assertEquals(orderDTO.getStatus(), result.getStatus());
    }

    @Test
    void testGetOrderById_NotFound() {
        when(orderRepository.findById(1L)).thenReturn(Optional.empty());

        OrderNotFoundException thrown = assertThrows(OrderNotFoundException.class, () -> orderService.getOrderById(1L));
        assertEquals("Order not found with id: 1", thrown.getMessage());
    }

    @Test
    void testGetOrdersByUserId() {
        Order order1 = new Order();
        order1.setId(1L);
        order1.setStatus(OrderStatus.PENDING);
        order1.setProductSummary(new ArrayList<>());
        Order order2 = new Order();
        order2.setId(2L);
        order2.setStatus(OrderStatus.COMPLETED);
        order2.setProductSummary(new ArrayList<>());

        OrderDTO d1 = OrderMapper.toDTO(order1);
        OrderDTO d2 = OrderMapper.toDTO(order2);

        when(orderRepository.findByUserId(1L)).thenReturn(Arrays.asList(order1, order2));

        List<OrderDTO> orderDTOs = orderService.getOrdersByUserId(1L);

        assertNotNull(orderDTOs);
        assertEquals(2, orderDTOs.size());
        assertTrue(orderDTOs.contains(d1));
        assertTrue(orderDTOs.contains(d2));
    }

    @Test
    void testUpdateOrder() {
        Long id = 1L;
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setStatus(OrderStatus.COMPLETED.name());
        orderDTO.setProductSummary(new ArrayList<>());
        Order existingOrder = new Order();
        existingOrder.setId(id);
        existingOrder.setStatus(OrderStatus.PENDING);

        when(orderRepository.findById(id)).thenReturn(Optional.of(existingOrder));
        when(orderRepository.save(existingOrder)).thenReturn(existingOrder);

        OrderDTO updatedOrderDTO = orderService.updateOrder(id, orderDTO);

        assertNotNull(updatedOrderDTO);
        assertEquals(OrderStatus.COMPLETED.name(), updatedOrderDTO.getStatus());
    }

    @Test
    void testDeleteOrder() {
        Long id = 1L;
        Order existingOrder = new Order();
        existingOrder.setId(id);

        // Mocking the repository to return the existing order
        when(orderRepository.existsById(id)).thenReturn(true);
        doNothing().when(orderRepository).deleteById(id);

        // Call the method under test
        orderService.deleteOrder(id);

        // Verify that deleteById was called once
        verify(orderRepository, times(1)).deleteById(id);
    }

    @Test
    void testDeleteOrder_NotExists_ThrowsException() {
        Long id = 1L;

        when(orderRepository.existsById(id)).thenReturn(false);

        assertThrows(OrderNotFoundException.class, () -> orderService.deleteOrder(id));
    }

}
