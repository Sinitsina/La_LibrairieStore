package ru.lalibrairiestore.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lalibrairiestore.dto.OrderDTO;
import ru.lalibrairiestore.dto.OrderDetailsDTO;
import ru.lalibrairiestore.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@Api("Order controller")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/make-order")
    @ApiOperation("make order")
    public ResponseEntity<OrderDTO> makeOrder(@RequestBody OrderDetailsDTO orderDetailsDTO, Long userId) {

        return new ResponseEntity<>(orderService.makeOrder(orderDetailsDTO, userId), HttpStatus.OK);
    }

    @PutMapping("/cancel-order/{orderId}")
    @ApiOperation("cancel order")
    public ResponseEntity<OrderDTO> cancelOrder(@PathVariable Long orderId) {

        return new ResponseEntity<>(orderService.cancelOrder(orderId), HttpStatus.OK);
    }

    @GetMapping("/find-order/{orderId}")
    @ApiOperation("find order by id")
    public OrderDTO findOrderById(@PathVariable Long orderId) {

        return orderService.findOrderById(orderId);
    }

    @GetMapping("/user-orders/{userId}")
    @ApiOperation("get all orders")
    public List<OrderDTO> findAllUserOrders(@PathVariable Long userId) {

        return orderService.findAllUserOrders(userId);
    }

}
