package org.example.response;

public class SuccessResponse extends Response{
    public SuccessResponse(String message) {
        super(true, message);
    }
}
