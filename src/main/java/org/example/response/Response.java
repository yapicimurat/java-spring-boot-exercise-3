package org.example.response;

public abstract class Response {
    public boolean isSuccess;
    public String message;
    public Response(boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }
}
