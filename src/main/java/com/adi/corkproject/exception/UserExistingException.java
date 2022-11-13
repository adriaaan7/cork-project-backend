package com.adi.corkproject.exception;

public class UserExistingException extends RuntimeException{
    public UserExistingException(String message) {
        super(message);
    }
}
