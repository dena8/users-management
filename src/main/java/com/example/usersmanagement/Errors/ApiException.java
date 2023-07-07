package com.example.usersmanagement.Errors;

public class ApiException extends Exception {
    private final ErrorMessage errorMessage;

    public ApiException(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }

    public int getStatus() {
        return this.errorMessage.getCode();
    }
}
