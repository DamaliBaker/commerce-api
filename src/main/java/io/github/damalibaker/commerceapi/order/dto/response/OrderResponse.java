package io.github.damalibaker.commerceapi.order.dto.response;

import io.github.damalibaker.commerceapi.order.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private Long id;
    private OrderStatus status;
    private BigDecimal subtotal;
    private LocalDateTime createdAt;
    private List<OrderItemResponse> items;
}
