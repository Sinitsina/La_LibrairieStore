package ru.lalibrairiestore.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lalibrairiestore.dto.OrderDTO;
import ru.lalibrairiestore.dto.OrderDetailsDTO;
import ru.lalibrairiestore.service.OrderService;

import javax.annotation.security.RolesAllowed;
import java.util.List;

import static ru.lalibrairiestore.model.Role.ROLE_ADMIN;
import static ru.lalibrairiestore.model.Role.ROLE_CUSTOMER;

@RestController
@RequestMapping("/api/order")
@Api(value = "Order", tags = {"Order"})
public class OrderController {

    private OrderService orderService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @RolesAllowed({ROLE_CUSTOMER})
    @PostMapping("/make-order")
    @ApiOperation("make order")
    public ResponseEntity<OrderDTO> makeOrder(@RequestBody OrderDetailsDTO orderDetailsDTO) {

        return new ResponseEntity<>(orderService.makeOrder(orderDetailsDTO), HttpStatus.OK);
    }

    @RolesAllowed({ROLE_CUSTOMER})
    @PutMapping("/cancel-order/{orderId}")
    @ApiOperation("cancel order")
    public ResponseEntity<OrderDTO> cancelOrder(@PathVariable Long orderId) {

        return new ResponseEntity<>(orderService.cancelOrder(orderId), HttpStatus.OK);
    }

    @RolesAllowed({ROLE_ADMIN})
    @PutMapping("/complete-order/{orderId}")
    @ApiOperation("complete order")
    public ResponseEntity<OrderDTO> completeOrder(@PathVariable Long orderId) {

        return new ResponseEntity<>(orderService.completeOrder(orderId), HttpStatus.OK);
    }

    @RolesAllowed({ROLE_ADMIN})
    @GetMapping("/find-order/{orderId}")
    @ApiOperation("find order by id")
    public OrderDTO findOrderById(@PathVariable Long orderId) {

        return orderService.findOrderById(orderId);
    }

    @RolesAllowed({ROLE_CUSTOMER})
    @GetMapping("/user-orders")
    @ApiOperation("get all orders")
    public List<OrderDTO> findAllUserOrders() {

        return orderService.findAllUserOrders();
    }
}
