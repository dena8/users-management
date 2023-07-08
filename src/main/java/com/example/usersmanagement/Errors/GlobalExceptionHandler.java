package com.example.usersmanagement.Errors;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@ControllerAdvice
public class GlobalExceptionHandler {
    private final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> handleApiException(ApiException e) {
        LOGGER.error(e.getErrorMessage().getMessage(), e);
        return new ErrorResponse(e).getResponse();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException e) {
        LOGGER.error(e.getMessage(), e);
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        List<ErrorMessage> messages = new ArrayList<>();
        for (ConstraintViolation<?> violation : constraintViolations) {
            messages.add(new ErrorMessage(404, violation.getMessage()));
        }

        return new ErrorResponse(messages).getResponse();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandler(Exception e) {
        LOGGER.error(e.getMessage(), e);
        return new ErrorResponse().getResponse();
    }
}
