package io.github.damalibaker.commerceapi.exception.cart;

public class InvalidCartQuantityException extends RuntimeException {
    public InvalidCartQuantityException() {
        super("Cart item quantity must be greater than 0");
    }
}
