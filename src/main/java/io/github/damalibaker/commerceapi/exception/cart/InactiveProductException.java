package io.github.damalibaker.commerceapi.exception.cart;

public class InactiveProductException extends RuntimeException {
    public InactiveProductException(String productName) {
        super("Product '" + productName + "' is currently" +
                " inactive and cannot be added to cart");
    }
}
