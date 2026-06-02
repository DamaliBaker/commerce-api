package io.github.damalibaker.commerceapi.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long id) {
        super("Unable to find product id #" + id);
    }
}
