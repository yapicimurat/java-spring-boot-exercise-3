package org.example.exception;

public class RoleNotFoundException extends EntityException{
    public RoleNotFoundException(String message) {
        super(message);
    }
}
