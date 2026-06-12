package io.github.damalibaker.commerceapi.exception.user;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String email) {
        super("The email '" + email + "' is already taken.");
    }
}
