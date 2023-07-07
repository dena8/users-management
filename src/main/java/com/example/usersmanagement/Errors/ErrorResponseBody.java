package com.example.usersmanagement.Errors;

public class ErrorResponseBody<T> {
    private final T errors;

    public ErrorResponseBody(T errors) {
        this.errors = errors;
    }

    public T getErrors() {
        return errors;
    }
}
