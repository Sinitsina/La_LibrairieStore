package ru.lalibrairiestore.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lalibrairiestore.dto.OrderDTO;
import ru.lalibrairiestore.dto.OrderDetailsDTO;
import ru.lalibrairiestore.exceptions.BadRequestException;
import ru.lalibrairiestore.exceptions.EntityNotFoundException;
import ru.lalibrairiestore.mapper.OrderMapper;
import ru.lalibrairiestore.model.CartItem;
import ru.lalibrairiestore.model.Order;
import ru.lalibrairiestore.model.OrderStatus;
import ru.lalibrairiestore.repository.OrderRepository;
import ru.lalibrairiestore.security.JwtUser;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private OrderRepository orderRepository;

    private OrderMapper orderMapper;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Autowired
    public void setOrderMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    /**
     * Make an order
     */
    public OrderDTO makeOrder(OrderDetailsDTO orderDetailsDTO) {

        Long userId = JwtUser.getCurrentUserID();
        Order order = orderRepository.findByUserIdAndOrderStatus(userId, OrderStatus.CREATED);

        if (order == null) {
            throw new BadRequestException("Cart item is empty!");
        }

        order.setTotalCost(calculateOrderPrice(order));
        order.setAddress(orderDetailsDTO.getAddress());
        order.setPhoneNumber(orderDetailsDTO.getPhoneNumber());
        order.setDateOrder(new Date());
        order.setOrderStatus(OrderStatus.ACTIVE);

        orderRepository.save(order);
        log.info("IN makeOrder {} order was created", order);

        return orderMapper.orderToOrderDTO(order);
    }

    /**
     * Cancel the order
     */
    public OrderDTO cancelOrder(Long orderId) {

        // Order order = orderRepository.getOne(orderId);
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order id " + orderId + " was not found"));

        order.setOrderStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);

        log.info("IN cancelOrder for {} order status was changed to cancelled", order);

        return orderMapper.orderToOrderDTO(order);
    }

    /**
     * Make order status completed
     */
    public OrderDTO completeOrder(Long orderId) {

        Order order = orderRepository.getOne(orderId);

        order.setOrderStatus(OrderStatus.COMPLETED);

        orderRepository.save(order);

        log.info("IN completeOrder for {} order status was changed to completed", order);

        return orderMapper.orderToOrderDTO(order);
    }

    /**
     * Calculation of all products in cart
     */
    public BigDecimal calculateOrderPrice(Order order) {

        List<CartItem> cartItemList = order.getCartItems();

//        Get stream of product in CartItem, each item multiply quantity, sum all costs from previous step
//        and return result
        return cartItemList.stream()
                .map(e -> e.getProduct().getPrice().multiply(new BigDecimal(e.getCount())))
                .reduce(BigDecimal::add)
                .orElse(new BigDecimal(0));
    }

    /**
     * Find order by order id
     */
    public OrderDTO findOrderById(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> {
                    log.info("IN findOrderById order id: {} wasn't found", orderId);
                    return new EntityNotFoundException("Order id " + orderId + " was not found");
                });

        log.info("IN findOrderById {} order was found", order);

        return orderMapper.orderToOrderDTO(order);
    }

    /**
     * Show all user's orders
     */
    public List<OrderDTO> findAllUserOrders() {

        List<Order> userOrders = orderRepository.findAllByUserId(JwtUser.getCurrentUserID());

        log.info("IN findAllUserOrders {} user orders were found", userOrders.size());

        return orderMapper.ordersToOrdersDTO(userOrders);
    }
}
