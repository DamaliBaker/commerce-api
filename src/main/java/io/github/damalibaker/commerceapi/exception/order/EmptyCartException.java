package io.github.damalibaker.commerceapi.exception.order;

public class EmptyCartException extends RuntimeException {
    public EmptyCartException() {
        super("Cannot checkout with an empty cart");
    }
}
