package io.github.damalibaker.commerceapi.exception.category;

public class CategoryAlreadyExistsException extends RuntimeException {
    public CategoryAlreadyExistsException(String name){
        super("The category '" + name + "' already exists");
    }
}
