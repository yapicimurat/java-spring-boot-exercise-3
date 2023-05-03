package org.example.exception;

public class UserNotFoundException extends EntityException{
    public UserNotFoundException(String message) {
        super(message);
    }
}
