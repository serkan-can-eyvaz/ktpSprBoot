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
    void setUp() {
        // Test verilerini
        bookRepository.deleteAll();
        writerRepository.deleteAll();

        Writer writer = new Writer();
        writer.setName("John");
        writer.setSurname("Doe");
        writer = writerRepository.save(writer);

        Book book = new Book();
        book.setTitle("Sample Book");
        book.setWriter(writer);
        bookRepository.save(book);
    }

    @Test
    void Create_Success() {
        CreateBookRequest createBookRequest = new CreateBookRequest();
        createBookRequest.setTitle("New Sample Book");
        createBookRequest.setWriterId(1); // Test verisinin ID'si

        Book createdBook = bookService.create(createBookRequest);

        assertNotNull(createdBook);
        assertEquals("New Sample Book", createdBook.getTitle());
        assertEquals(1, createdBook.getWriter().getId());
        assertNotNull(createdBook.getId(), "Book ID should not be null after creation");
        assertTrue(createdBook.getTitle().length() > 0, "Book title should not be empty");
        assertNotNull(createdBook.getWriter(), "Book writer should not be null");
    }

    @Test
    void GetAll_Success() {
        List<GetAllBookResponse> getAllBookResponses = bookService.getAll();

        assertFalse(getAllBookResponses.isEmpty(), "The list of books should not be empty");
        assertEquals(1, getAllBookResponses.size(), "There should be 1 book in the list");

        GetAllBookResponse response = getAllBookResponses.get(0);
        assertNotNull(response.getTitle(), "Book title should not be null");
    }

    @Test
    void GetById_Success() {
        CreateBookRequest createBookRequest = new CreateBookRequest();
        createBookRequest.setTitle("Another Sample Book");
        createBookRequest.setWriterId(1);
        Book createdBook = bookService.create(createBookRequest);

        GetByIdBookResponse getByIdBookResponse = bookService.getById(createdBook.getId());

        assertNotNull(getByIdBookResponse, "Book response should not be null");
        assertEquals("Another Sample Book", getByIdBookResponse.getTitle(), "Book title should match");
        assertNotNull(getByIdBookResponse.getId(), "Book ID should not be null");
    }

    @Test
    void DeleteById_Success() {
        CreateBookRequest createBookRequest = new CreateBookRequest();
        createBookRequest.setTitle("Book to Delete");
        createBookRequest.setWriterId(1);
        Book createdBook = bookService.create(createBookRequest);

        bookService.deleteById(createdBook.getId());

        assertThrows(RuntimeException.class, () -> bookService.getById(createdBook.getId()), "Fetching deleted book should throw an exception");
        assertTrue(bookRepository.findById(createdBook.getId()).isEmpty(), "Deleted book should not be found in the repository");
    }
}
