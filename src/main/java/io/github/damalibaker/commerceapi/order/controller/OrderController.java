package io.github.damalibaker.commerceapi.order.controller;

import io.github.damalibaker.commerceapi.order.dto.response.OrderResponse;
import io.github.damalibaker.commerceapi.order.mapper.OrderMapper;
import io.github.damalibaker.commerceapi.order.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @GetMapping
    public List<OrderResponse> getOrdersForCurrentUser() {
        return orderService.getOrdersForCurrentUser()
                .stream()
                .map(orderMapper::toOrderResponse)
                .toList();
    }

    @GetMapping("/{orderId}")
    public OrderResponse getOrderForCurrentUser(@PathVariable Long orderId) {
        return orderMapper.toOrderResponse(orderService.getOrderForCurrentUser(orderId));
    }

    @PostMapping("/checkout")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse checkout() {
        return orderMapper.toOrderResponse(orderService.checkout());
    }
}
