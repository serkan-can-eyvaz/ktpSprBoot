package com.example.staj1gun.integrationTest.service;

import com.example.staj1gun.dao.BookRepository;
import com.example.staj1gun.dao.WriterRepository;
import com.example.staj1gun.dto.request.CreateBookRequest;
import com.example.staj1gun.dto.response.GetAllBookResponse;
import com.example.staj1gun.dto.response.GetByIdBookResponse;
import com.example.staj1gun.entity.Book;
import com.example.staj1gun.entity.Writer;
import com.example.staj1gun.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Transactional
public class BookServiceIntegrationTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private WriterRepository writerRepository;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach

    void setUp() { // Test verilerini
        bookRepository.deleteAll();
        writerRepository.deleteAll();

        Writer writer = new Writer();
        writer.setName("John");
        writer.setSurname("Doe");
        writerRepository.save(writer);

        Book book = new Book();
        book.setTitle("Sample Book");
        book.setWriter(writer);
        bookRepository.save(book);
    }

    @Test
    void testCreate_Success() {
        CreateBookRequest createBookRequest = new CreateBookRequest();
        createBookRequest.setTitle("Sample Book");
        createBookRequest.setWriterId(1); // Test verinisin ID'si

        Book createdBook = bookService.create(createBookRequest);

        assertNotNull(createdBook);
        assertEquals("Sample Book", createdBook.getTitle());
        assertEquals(1, createdBook.getWriter().getId());
    }

    @Test
    void testGetAll_Success() {
        //arrange
        setUp();

        //act
        List<GetAllBookResponse> getAllBookResponses = bookService.getAll();

        //assert
        assertFalse(getAllBookResponses.isEmpty(), "The list of writers should not be empty");
        assertEquals(1, getAllBookResponses.size(), "There should be 2 writers in the list");
    }

    @Test
    void testGetById_Success() {
        CreateBookRequest createBookRequest = new CreateBookRequest();
        createBookRequest.setTitle("Sample Book");
        createBookRequest.setWriterId(1);
        Book createdBook = bookService.create(createBookRequest);

        GetByIdBookResponse getByIdBookResponse = bookService.getById(createdBook.getId());

        assertNotNull(getByIdBookResponse);
        assertEquals("Sample Book", getByIdBookResponse.getTitle());
    }

    @Test
    void testDeleteById_Success() {
        // Önce kitap oluşturup ID'sini alın
        CreateBookRequest createBookRequest = new CreateBookRequest();
        createBookRequest.setTitle("Sample Book");
        createBookRequest.setWriterId(1);
        Book createdBook = bookService.create(createBookRequest);

        bookService.deleteById(createdBook.getId());

        assertThrows(RuntimeException.class, () -> bookService.getById(createdBook.getId()));
    }
}
