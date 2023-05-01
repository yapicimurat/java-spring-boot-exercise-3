package org.example.exception;

public abstract class EntityException extends RuntimeException {
    private String message;
    public EntityException(String message) {
        super(message);
        this.message = message;
    }

    public void handleException() {
        System.out.println(message);
    }
}
