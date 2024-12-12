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

    public Books addBook(Books book) {
        if (book.getPrice() < 0) {
            throw new IllegalArgumentException(PRICE_CANT_NEGATIVE);
        }
        return bookRepository.save(book);
    }

    public List<Books> getAllBooks() {
        return bookRepository.findAll();
    }

    public Books getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(BOOK_WITH_ID + id + NOT_FOUND));
    }

    public Books updateBook(Long id, Books book) {
        if (book.getPrice() < 0) {
            throw new IllegalArgumentException(PRICE_CANT_NEGATIVE);
        }
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException(BOOK_NOT_FOUND);
        }
        book.setId(id);
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException(BOOK_WITH_ID + id + NOT_FOUND);
        }
        bookRepository.deleteById(id);
    }
}
