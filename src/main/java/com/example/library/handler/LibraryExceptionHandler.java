package com.example.library.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class LibraryExceptionHandler {

    /* This method handles the exception and return the error, with status code and error message.*/
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> handleResponseStatusException(ResponseStatusException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                "ERROR-" + ex.getStatusCode().value(),
                ex.getStatusCode().value(),
                ex.getReason()
        );
        return new ResponseEntity<>(errorResponse, ex.getStatusCode());
    }

    /* This method handles all validations of book details:
         1. Validate that the price is non-negative
         2. Validate the book name can't be null or empty
         3. Validate the author name can't be null or empty
       It will throw 500 status code on any validation failed with proper message
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> validationErrors = ex.getBindingResult().getAllErrors().stream()
                .map(error -> {
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    return fieldName + ": " + errorMessage;
                })
                .collect(Collectors.toList());

        ErrorResponse errorResponse = new ErrorResponse(
                "ERROR-500",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Validation failed",
                validationErrors
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    /* This method throws 500 status code on any exception. */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                "ERROR-500",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error: " + ex.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /* This method throws 404 status code on Handle BookNotFoundException (custom exception for missing book) */
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleBookNotFoundException(BookNotFoundException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                "ERROR-404",
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

}
