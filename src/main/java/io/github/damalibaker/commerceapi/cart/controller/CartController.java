package io.github.damalibaker.commerceapi.cart.controller;

import io.github.damalibaker.commerceapi.cart.dto.request.AddCartItemRequest;
import io.github.damalibaker.commerceapi.cart.dto.request.UpdateCartItemRequest;
import io.github.damalibaker.commerceapi.cart.dto.response.CartResponse;
import io.github.damalibaker.commerceapi.cart.mapper.CartMapper;
import io.github.damalibaker.commerceapi.cart.service.CartService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;
    private final CartMapper cartMapper;

    public CartController(CartService cartService,
                          CartMapper cartMapper) {
        this.cartService = cartService;
        this.cartMapper = cartMapper;
    }

    @GetMapping
    public CartResponse getCurrentUserCart() {
        return cartMapper.toCartResponse(cartService.getCurrentUserCart());
    }

    @PostMapping("/items")
    @ResponseStatus(HttpStatus.CREATED)
    public CartResponse addItemToCart(@RequestBody @Valid AddCartItemRequest request) {
        return cartMapper.toCartResponse(cartService.addItemToCart(request));
    }

    @PatchMapping("/items/{itemId}")
    public CartResponse updateCartItem(@PathVariable Long itemId,
                                       @Valid @RequestBody UpdateCartItemRequest request) {
        return cartMapper.toCartResponse(cartService.updateCartItem(itemId, request));
    }

    @DeleteMapping("/items/{itemId}")
    public CartResponse removeCartItem(@PathVariable Long itemId) {
        return cartMapper.toCartResponse(cartService.removeCartItem(itemId));
    }

    @DeleteMapping
    public CartResponse clearCart() {
        return cartMapper.toCartResponse(cartService.clearCart());
    }
}
