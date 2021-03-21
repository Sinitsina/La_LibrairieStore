package ru.lalibrairiestore.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.lalibrairiestore.dto.OrderDTO;
import ru.lalibrairiestore.dto.OrderDetailsDTO;
import ru.lalibrairiestore.model.Order;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderDTO orderToOrderDTO(Order order);

    Order orderDTOtoOrder(OrderDTO orderDTO);

    List<OrderDTO> ordersToOrdersDTO(List<? extends Order> orders);

    Order OrderDetailsDTOToOrder(OrderDetailsDTO orderDetailsDTO);

}
