package com.example.library.service;

import com.example.library.handler.BookNotFoundException;
import com.example.library.persistence.entity.Books;
import com.example.library.persistence.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    public static final String PRICE_CANT_NEGATIVE = "Price cannot be negative";
    public static final String BOOK_WITH_ID = "Book with ID ";
    public static final String NOT_FOUND = " is not found";
    public static final String BOOK_NOT_FOUND = "Book is not found";

    @Autowired
    private BookRepository bookRepository;

    /* This method is responsible to add a new book, on success give 200 ok  and on error or exception it gives 500 with error message*/
    public Books addBook(Books book) {
        if (book.getPrice() < 0) {
            // It will throw exception written in controller advice class LibraryExceptionHandler.java with 500 error code and error message
            throw new IllegalArgumentException(PRICE_CANT_NEGATIVE);
        }
        return bookRepository.save(book);
    }


    /* This method is responsible to get all books, on success give 200 ok */
    public List<Books> getAllBooks() {
        return bookRepository.findAll();
    }


    /* This method is responsible to get details of book by id, on success give 200 ok and it gives 404 with error message when book not found */
    public Books getBookById(Long id) {
        // It will throw exception written in controller advice class LibraryExceptionHandler.java with 404 error code and error message
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(BOOK_WITH_ID + id + NOT_FOUND));
    }


    /* This method is responsible to update details of existing book by id, on success give 200 ok and it gives 404 with error message when book not found */
    public Books updateBook(Long id, Books book) {
        // It will throw exception written in controller advice class LibraryExceptionHandler.java with 500 or 404 error code and error message
        if (book.getPrice() < 0) {
            throw new IllegalArgumentException(PRICE_CANT_NEGATIVE);
        }
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException(BOOK_NOT_FOUND);
        }
        book.setId(id);
        return bookRepository.save(book);
    }


    /* This method is responsible to delete details of existing book by id, on success give 204 and it gives 404 with error message when book not found */
    public void deleteBook(Long id) {
        // It will throw exception written in controller advice class LibraryExceptionHandler.java with 404 error code and error message
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException(BOOK_WITH_ID + id + NOT_FOUND);
        }
        bookRepository.deleteById(id);
    }
}
