package com.example.usersmanagement.Errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import java.util.ArrayList;
import java.util.List;

import static com.example.usersmanagement.Utils.Constants.GLOBAL_ERROR_MESSAGE;

public class ErrorResponse {
    private final HttpStatus httpStatus;
    private  List<ErrorMessage> errors = new ArrayList<>();

    public ErrorResponse(ApiException error) {
        this.errors.add(error.getErrorMessage());
        this.httpStatus = setHttpStatus(error.getStatus());
    }

    public ErrorResponse(List<ErrorMessage> errors) {
        this.errors = errors;
        this.httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
    }

    public ErrorResponse() {
        this.errors.add(GLOBAL_ERROR_MESSAGE);
        this.httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
    }

    public ErrorResponse(Errors errors) {
        this.httpStatus=HttpStatus.UNPROCESSABLE_ENTITY;
    }


    public ResponseEntity<ErrorResponseBody<List<ErrorMessage>>> getResponse() {
        return new ResponseEntity<>(new ErrorResponseBody<>(errors), httpStatus);
    }

    public HttpStatus setHttpStatus(int code) {
        HttpStatus resolvedStatus = HttpStatus.resolve(code);
        return resolvedStatus == null ? HttpStatus.UNPROCESSABLE_ENTITY : resolvedStatus;
    }
}
