package org.sid.services;

import org.hibernate.exception.ConstraintViolationException;
import org.sid.domain.ApiError;
import org.sid.exception.ResourceNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Collections;

@ControllerAdvice
public class RestServiceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Object> handleEmptyResultData(final EmptyResultDataAccessException ex) {
        ApiError apiError = ApiError.builder()
                .dateTime(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND)
                .message(ex.getMessage())
                .subErrors(Collections.emptyList())
                .build();
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFound(final ResourceNotFoundException ex) {
        ApiError apiError = ApiError.builder()
                .dateTime(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND)
                .message("Resource not found")
                .subErrors(Collections.emptyList())
                .build();
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolations(final ConstraintViolationException ex) {
        ApiError apiError = ApiError.builder()
                .dateTime(LocalDateTime.now())
                .status(HttpStatus.CONFLICT)
                .message(ex.getMessage())
                .debugMessage(ex.getCause().toString())
                .subErrors(Collections.emptyList())
                .build();
        return buildResponseEntity(apiError);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatusCode status, final WebRequest request) {
        ApiError apiError = ApiError.builder()
                .dateTime(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST)
                .message(ex.getMessage())
                .debugMessage(ex.getLocalizedMessage())
                .build();
        apiError.addValidationErrors(ex.getBindingResult().getFieldErrors());
        apiError.addValidationError(ex.getBindingResult().getGlobalErrors());
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(final ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
