package com.hmk.challenge.bank.web.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hmk.challenge.bank.business.domain.exception.InsufficientBalanceException;
import com.hmk.challenge.bank.business.domain.exception.NotFoundException;
import com.hmk.challenge.bank.business.domain.exception.UnauthorizedTransactionException;
import com.hmk.challenge.bank.business.domain.exception.ValidationException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        var errorResponse = buildErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY,
                "Validation error. Check 'errors' field for details.");
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errorResponse.getErrorDetails().addValidationError(fieldError.getField(),
                    fieldError.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorResponse);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleInsufficientBalanceException(InsufficientBalanceException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()));
    }

    @ExceptionHandler(UnauthorizedTransactionException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleUnauthorizedTransactionException(UnauthorizedTransactionException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()));
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleUnauthorizedTransactionException(ValidationException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()));
    }

    private static ErrorResponse buildErrorResponse(HttpStatus httpStatus, String message) {
        return ErrorResponse.builder()
                .statusCode(httpStatus.value())
                .errorMessage(httpStatus.getReasonPhrase())
                .errorDetails(ErrorResponse.ErrorDetails.builder()
                        .message(message)
                        .build())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @Builder
    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ErrorResponse {
        private int statusCode;
        private String errorMessage;
        private ErrorDetails errorDetails;
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        private LocalDateTime timestamp;

        @Builder
        @Data
        @AllArgsConstructor
        private static class ErrorDetails {
            private String message;
            private List<ValidationError> errors;

            public void addValidationError(String field, String message) {
                if (Objects.isNull(errors)) {
                    errors = new ArrayList<>();
                }
                errors.add(new ValidationError(field, message));
            }
        }

        @Data
        @AllArgsConstructor
        private static class ValidationError {
            private String field;
            private String message;
        }
    }
}
