package ru.lalibrairiestore.exceptions;

public class AuthenticationServiceException extends RuntimeException {

    public AuthenticationServiceException(String message) {
        super(message);
    }
}
