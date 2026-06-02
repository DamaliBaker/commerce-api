package io.github.damalibaker.commerceapi.exception;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(Long id) {
        super("Category id #" + id + " cannot be found.");
    }
}
