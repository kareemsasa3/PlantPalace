package com.sasa.backend.dto.order;

public class ApiResponse<T> {
    private T data;
    private String error;

    public ApiResponse(T data, String error) {
        this.data = data;
        this.error = error;
    }

    // Getter and setter
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
