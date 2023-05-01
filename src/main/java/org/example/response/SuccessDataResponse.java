package org.example.response;

public class SuccessDataResponse<T> extends DataResponse<T>{
    public SuccessDataResponse(T data, String message) {
        super(true, message, data);
    }
}
