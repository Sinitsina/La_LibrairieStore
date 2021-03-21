package ru.lalibrairiestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lalibrairiestore.model.Order;
import ru.lalibrairiestore.model.OrderStatus;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findByUserIdAndOrderStatus(Long id, OrderStatus orderStatus);

    List<Order> findAllByUserId(Long id);
}
