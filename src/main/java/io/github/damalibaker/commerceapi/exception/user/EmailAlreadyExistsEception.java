package io.github.damalibaker.commerceapi.exception.user;

public class EmailAlreadyExistsEception extends RuntimeException {
    public EmailAlreadyExistsEception(String email) {
        super("The email '" + email + "' is already taken.");
    }
}
