package com.example.library;

import com.example.library.controller.BookController;
import com.example.library.persistence.entity.Books;
import com.example.library.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    private Books book;

    @BeforeEach
    void setUp() {
        book = new Books(null, "Spring Boot", "Author 1", 25.99);
    }

    @Test
    void testAddBook() throws Exception {
        when(bookService.addBook(any(Books.class))).thenReturn(new Books(1L, "Spring Boot", "Author 1", 25.99));

        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Spring Boot\",\"author\":\"Author 1\",\"price\":25.99}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Spring Boot"))
                .andExpect(jsonPath("$.author").value("Author 1"))
                .andExpect(jsonPath("$.price").value(25.99));
    }

    @Test
    void testGetBookById() throws Exception {
        String validBookJson = "{\"title\": \"Sample Book\", \"author\": \"Author 1\", \"price\": 10.5}";
        String response = mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validBookJson))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String bookId = response.substring(response.indexOf("\"id\":") + 5, response.indexOf(","));

        mockMvc.perform(get("/books/" + bookId))
                .andExpect(status().isOk())  // Expect 200 OK
                .andExpect(jsonPath("$.title").value("Sample Book"))
                .andExpect(jsonPath("$.author").value("Author 1"))
                .andExpect(jsonPath("$.price").value(10.5));
    }


}

