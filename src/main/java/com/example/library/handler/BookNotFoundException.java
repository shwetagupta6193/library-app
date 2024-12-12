package com.example.library.handler;

/* Custom Exception class for Book Not Found */
public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String message) {
        super(message);
    }
}
