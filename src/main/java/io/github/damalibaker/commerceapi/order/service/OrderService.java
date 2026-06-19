package io.github.damalibaker.commerceapi.order.service;

import io.github.damalibaker.commerceapi.order.entity.OrderEntity;

import java.util.List;

public interface OrderService {
    OrderEntity checkout();
    List<OrderEntity> getOrdersForCurrentUser();
    OrderEntity getOrderForCurrentUser(Long orderId);
}
