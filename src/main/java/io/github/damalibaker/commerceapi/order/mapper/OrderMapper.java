package io.github.damalibaker.commerceapi.order.mapper;

import io.github.damalibaker.commerceapi.order.dto.response.OrderItemResponse;
import io.github.damalibaker.commerceapi.order.dto.response.OrderResponse;
import io.github.damalibaker.commerceapi.order.entity.OrderEntity;
import io.github.damalibaker.commerceapi.order.entity.OrderItemEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {
    public OrderResponse toOrderResponse(OrderEntity order) {
        List<OrderItemResponse> items = order.getItems()
                .stream()
                .map(this::toOrderItemResponse)
                .toList();

        return new OrderResponse(
                order.getId(),
                order.getStatus(),
                order.getSubtotal(),
                order.getCreatedAt(),
                items
        );
    }

    public OrderItemResponse toOrderItemResponse(OrderItemEntity item) {
        return new OrderItemResponse(
                item.getId(),
                item.getProduct().getId(),
                item.getProductName(),
                item.getUnitPrice(),
                item.getQuantity(),
                item.getLineTotal()
        );
    }
}
