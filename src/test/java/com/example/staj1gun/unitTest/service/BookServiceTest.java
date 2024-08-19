package com.example.staj1gun.unitTest.service;

import com.example.staj1gun.dao.BookRepository;
import com.example.staj1gun.dao.WriterRepository;
import com.example.staj1gun.dto.mapper.BookMapper;
import com.example.staj1gun.dto.request.CreateBookRequest;
import com.example.staj1gun.dto.response.GetAllBookResponse;
import com.example.staj1gun.dto.response.GetByIdBookResponse;
import com.example.staj1gun.entity.Book;
import com.example.staj1gun.entity.Writer;
import com.example.staj1gun.service.BookService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private WriterRepository writerRepository;


    @InjectMocks
    private BookService bookService;

    private BookMapper bookMapper;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        bookMapper = new BookMapper(); // BookMapper'ı manuel olarak oluşturuyoruz
        bookService = new BookService(bookRepository, writerRepository, bookMapper);
    }

    @Test
    void create_Success() {
        // Arrange
        CreateBookRequest createBookRequest = new CreateBookRequest();
        createBookRequest.setTitle("Sample Book");
        createBookRequest.setWriterId(1);

        Writer writer = new Writer();
        writer.setId(1);
        writer.setName("John");
        writer.setSurname("Doe");

        Book book = new Book();
        book.setTitle("Sample Book");
        book.setWriter(writer);

        when(writerRepository.findById(1)).thenReturn(Optional.of(writer));

        //mapper sınıfını mocklamadan onu da test et
        //when(bookMapper.toBook(createBookRequest, writer)).thenReturn(book);
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        // Act
        Book createdBook = bookService.create(createBookRequest);

        // Assert
        assertNotNull(createdBook);
        assertEquals("Sample Book", createdBook.getTitle());
        assertEquals(writer, createdBook.getWriter());

        verify(writerRepository, times(1)).findById(1);
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void testCreate_WriterNotFound() {
        // Arrange
        CreateBookRequest createBookRequest = new CreateBookRequest();
        createBookRequest.setWriterId(1);

        when(writerRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            bookService.create(createBookRequest);
        });

        assertEquals("Yazar bulunamadı, id: 1", exception.getMessage());
        verify(writerRepository, times(1)).findById(1);
        verify(bookRepository, never()).save(any(Book.class));
    }

    @Test
    void testGetAll() {
        // Arrange
        Writer writer = new Writer();
        writer.setName("John");
        writer.setSurname("Doe");

        Book book1 = new Book();
        book1.setTitle("Book 1");
        book1.setWriter(writer);

        Book book2 = new Book();
        book2.setTitle("Book 2");
        book2.setWriter(writer);

        List<Book> books = List.of(book1, book2);
        when(bookRepository.findAll()).thenReturn(books);

        GetAllBookResponse response1 = new GetAllBookResponse();
        response1.setTitle("Book 1");

        GetAllBookResponse response2 = new GetAllBookResponse();
        response2.setTitle("Book 2");

        // Act
        List<GetAllBookResponse> responses = bookService.getAll();

        // Assert
        assertNotNull(responses);
        assertEquals(2, responses.size());
        assertEquals("Book 1", responses.get(0).getTitle());
        assertEquals("Book 2", responses.get(1).getTitle());

        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void testGetById_BookExists() {
        // Arrange
        Writer writer = new Writer();
        writer.setId(1);
        writer.setName("John");
        writer.setSurname("Doe");

        Book book = new Book();
        book.setId(1);
        book.setTitle("Sample Book");
        book.setWriter(writer);

        GetByIdBookResponse response = new GetByIdBookResponse();
        response.setTitle("Sample Book");
        response.setWriterName("John");
        response.setWriterSurname("Doe");

        when(bookRepository.findById(1)).thenReturn(Optional.of(book));

        // Act
        GetByIdBookResponse result = bookService.getById(1);

        // Assert
        assertNotNull(result);
        assertEquals("Sample Book", result.getTitle());
        assertEquals("John", result.getWriterName());
        assertEquals("Doe", result.getWriterSurname());

        verify(bookRepository, times(1)).findById(1);
    }

    @Test
    void testGetById_BookNotFound() {
        // Arrange
        when(bookRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            bookService.getById(1);
        });

        assertEquals("Kitap bulunamadı, id: 1", exception.getMessage());
        verify(bookRepository, times(1)).findById(1);
    }

    @Test
    void testDeleteById_BookExists() {
        // Arrange
        Book book = new Book();
        book.setId(1);
        when(bookRepository.findById(1)).thenReturn(Optional.of(book));

        // Act
        bookService.deleteById(1);

        // Assert
        verify(bookRepository, times(1)).findById(1);
        verify(bookRepository, times(1)).delete(book);
    }

    @Test
    void testDeleteById_BookNotFound() {
        // Arrange
        when(bookRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            bookService.deleteById(1);
        });

        assertEquals("Kitap bulunamadı, id: 1", exception.getMessage());
        verify(bookRepository, times(1)).findById(1);
        verify(bookRepository, never()).delete(any(Book.class));
    }


}
