package org.example.response;

public class SuccessResponse extends Response{
    public SuccessResponse(boolean isSuccess, String message) {
        super(isSuccess, message);
    }
}
