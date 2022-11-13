package com.adi.corkproject.exception;

public class UserNotExistingException extends RuntimeException {
    public UserNotExistingException(String message) {
        super(message);
    }
}
