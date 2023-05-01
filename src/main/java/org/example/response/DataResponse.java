package org.example.response;

public abstract class DataResponse<T> extends Response {
    public T data;

    public DataResponse(boolean isSuccess, String message, T data) {
        super(isSuccess, message);
        this.data = data;
    }
}
