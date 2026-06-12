package io.github.damalibaker.commerceapi.exception.cart;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(String productName, int stock) {
        super("Not enough stock available for product '" + productName + "'. Available stock: " + stock);
    }
}
