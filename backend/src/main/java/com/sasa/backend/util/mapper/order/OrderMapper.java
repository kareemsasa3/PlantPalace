package com.sasa.backend.util.mapper.order;

import com.sasa.backend.dto.order.OrderDTO;
import com.sasa.backend.entity.order.Order;
import com.sasa.backend.entity.product.Product;
import com.sasa.backend.entity.product.CannabisProduct;
import com.sasa.backend.entity.user.Address;
import com.sasa.backend.entity.user.User;
import com.sasa.backend.util.mapper.product.CannabisProductMapper;
import com.sasa.backend.util.mapper.user.AddressMapper;
import com.sasa.backend.util.mapper.user.UserMapper;
import com.sasa.backend.dto.product.ProductDTO;
import com.sasa.backend.dto.product.CannabisProductDTO;
import com.sasa.backend.dto.user.AddressDTO;
import com.sasa.backend.dto.user.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {

    private OrderMapper() {
        throw new IllegalStateException("Util class");
    }

    public static OrderDTO toDTO(Order order) {
        if (order == null) {
            return null;
        }

        List<ProductDTO> productDTOs = order.getProductSummary().stream()
                .map(OrderMapper::mapProductToDTO)
                .collect(Collectors.toList());

        AddressDTO shippingAddressDTO = AddressMapper.toDTO(order.getShippingAddress());
        AddressDTO billingAddressDTO = AddressMapper.toDTO(order.getBillingAddress());
        UserDTO userDTO = UserMapper.toDTO(order.getUser());

        return new OrderDTO(
            order.getId(),
            order.getUser().getId(),
            order.getStatus(),
            order.getOrderReceivedTimestamp(),
            order.getExpectedDeliveryTimestamp(),
            shippingAddressDTO,
            billingAddressDTO,
            productDTOs,
            order.getPriceSummary(),
            userDTO
        );
    }

    public static Order toEntity(OrderDTO dto) {
        if (dto == null) {
            return null;
        }

        List<Product> products = dto.getProductSummary().stream()
                .map(OrderMapper::mapDTOToProduct)
                .collect(Collectors.toList());

        Address shippingAddress = AddressMapper.toEntity(dto.getShippingAddress());
        Address billingAddress = AddressMapper.toEntity(dto.getBillingAddress());
        User user = UserMapper.toEntity(dto.getUser());

        return new Order(
            dto.getId(),
            dto.getStatus(),
            dto.getOrderReceivedTimestamp(),
            dto.getExpectedDeliveryTimestamp(),
            shippingAddress,
            billingAddress,
            products,
            dto.getPriceSummary(),
            user
        );
    }

    private static ProductDTO mapProductToDTO(Product product) {
        if (product instanceof CannabisProduct) {
            return CannabisProductMapper.toDTO((CannabisProduct) product); // Assuming CannabisProductMapper exists
        }
        // Handle other Product subtypes here
        return null; // Or throw an exception, depending on your use case
    }

    private static Product mapDTOToProduct(ProductDTO dto) {
        if (dto instanceof CannabisProductDTO) {
            return CannabisProductMapper.toEntity((CannabisProductDTO) dto); // Assuming CannabisProductMapper exists
        }
        // Handle other ProductDTO subtypes here
        return null; // Or throw an exception, depending on your use case
    }
}