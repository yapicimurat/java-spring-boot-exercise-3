package org.example.response;

public class ErrorDataResponse extends DataResponse{
    public ErrorDataResponse(String message, Object data) {
        super(false, message, data);
    }
}
