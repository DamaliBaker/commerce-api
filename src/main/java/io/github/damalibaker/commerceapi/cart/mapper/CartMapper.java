package io.github.damalibaker.commerceapi.cart.mapper;

import io.github.damalibaker.commerceapi.cart.dto.response.CartItemResponse;
import io.github.damalibaker.commerceapi.cart.dto.response.CartResponse;
import io.github.damalibaker.commerceapi.cart.entity.CartEntity;
import io.github.damalibaker.commerceapi.cart.entity.CartItemEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class CartMapper {
    public CartResponse toCartResponse(CartEntity cart) {
        List<CartItemResponse> items = cart.getItems()
                .stream()
                .map(this::toCartItemResponse)
                .toList();

        BigDecimal subtotal = items.stream()
                .map(CartItemResponse::getLineTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new CartResponse(
                cart.getId(),
                items,
                subtotal
        );
    }

    public CartItemResponse toCartItemResponse(CartItemEntity item) {
        BigDecimal lineTotal = item.getProduct()
                .getPrice()
                .multiply(BigDecimal.valueOf(item.getQuantity()));

        return new CartItemResponse(
                item.getId(),
                item.getProduct().getId(),
                item.getProduct().getName(),
                item.getProduct().getPrice(),
                item.getQuantity(),
                lineTotal
        );
    }
}
