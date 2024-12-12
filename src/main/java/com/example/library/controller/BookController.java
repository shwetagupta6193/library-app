package com.example.library.controller;

import com.example.library.persistence.entity.Books;
import com.example.library.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    /* This method is responsible to add a new book, on success give 200 ok
       It handles all validations of book details:
         1. Validate that the price is non-negative
         2. Validate the book name can't be null or empty
         3. Validate the author name can't be null or empty
       It will throw 500 status code on any validation failed with proper message and 200 on success.
     */
    @PostMapping
    public ResponseEntity<Books> addBook(@Valid @RequestBody Books book) {
        Books savedBook = bookService.addBook(book);
        return new ResponseEntity<>(savedBook, HttpStatus.OK);
    }


    /* This method is responsible to give list of all books, on success give 200 ok */
    @GetMapping
    public ResponseEntity<List<Books>> getAllBooks() {
        List<Books> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }


    /* This method is responsible to get the book by id, on success give 200 ok and 404 when book is not found */
    @GetMapping("/{id}")
    public ResponseEntity<Books> getBookById(@PathVariable Long id) {
        Books book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }


    /* This method is responsible to update the existing book by id, on success give 200 ok and 404 when book is not found */
    @PutMapping("/{id}")
    public ResponseEntity<Books> updateBook(@PathVariable Long id, @RequestBody Books book) {
        Books updatedBook = bookService.updateBook(id, book);
        return ResponseEntity.ok(updatedBook);
    }


    /* This method is responsible to delete the existing book by id, on success give 204 and 404 when book is not found */
    @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}

