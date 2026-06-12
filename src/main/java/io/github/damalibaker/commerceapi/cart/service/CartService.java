package io.github.damalibaker.commerceapi.cart.service;

import io.github.damalibaker.commerceapi.cart.dto.request.AddCartItemRequest;
import io.github.damalibaker.commerceapi.cart.dto.request.UpdateCartItemRequest;
import io.github.damalibaker.commerceapi.cart.entity.CartEntity;

public interface CartService {
    CartEntity getCurrentUserCart();
    CartEntity addItemToCart(AddCartItemRequest request);
    CartEntity updateCartItem(Long itemId, UpdateCartItemRequest request);
    CartEntity removeCartItem(Long itemId);
    CartEntity clearCart();
}
