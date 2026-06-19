package io.github.damalibaker.commerceapi.exception.order;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(Long id) {
        super("Order ID #" + id + " was not found");
    }
}
