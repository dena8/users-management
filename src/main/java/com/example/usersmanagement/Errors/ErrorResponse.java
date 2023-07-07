package com.example.usersmanagement.Errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse {
    private final HttpStatus httpStatus;
    private final List<ErrorMessage> errors = new ArrayList<>();

    public ErrorResponse(ApiException error) {
        this.errors.add(error.getErrorMessage());
        this.httpStatus = setHttpStatus(error.getStatus());
    }


    public ResponseEntity<ErrorResponseBody<List<ErrorMessage>>> getResponse() {
        return new ResponseEntity<>(new ErrorResponseBody<>(errors), httpStatus);
    }

    public HttpStatus setHttpStatus(int code) {
        HttpStatus resolvedStatus = HttpStatus.resolve(code);
        return resolvedStatus == null ? HttpStatus.UNPROCESSABLE_ENTITY : resolvedStatus;
    }
}
