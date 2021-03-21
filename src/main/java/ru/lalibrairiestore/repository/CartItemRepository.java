package ru.lalibrairiestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lalibrairiestore.model.CartItem;
import ru.lalibrairiestore.model.Order;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    CartItem getByOrderAndProductId(Order order, Long id);
}
