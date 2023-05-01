package org.example.exception;

public class InvalidRoleException extends EntityException{
    public InvalidRoleException(String message) {
        super(message);
    }

    @Override
    public void handleException() {
        super.handleException();
    }
}
