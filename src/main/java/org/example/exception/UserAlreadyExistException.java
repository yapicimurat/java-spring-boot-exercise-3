package org.example.exception;

public class UserAlreadyExistException extends EntityException {
    public UserAlreadyExistException(String message) {
        super(message);
    }

    @Override
    public void handleException() {
        super.handleException();

    }
}
