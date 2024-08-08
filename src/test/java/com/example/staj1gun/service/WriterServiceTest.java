package com.example.staj1gun.service;

import com.example.staj1gun.dao.BookRepository;
import com.example.staj1gun.dao.WriterRepository;
import com.example.staj1gun.dto.request.CreateWriterRequest;
import com.example.staj1gun.dto.response.GetAllWriterResponse;
import com.example.staj1gun.entity.Book;
import com.example.staj1gun.entity.Writer;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class WriterServiceTest {

    @Mock
    private WriterRepository writerRepository;
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private WriterService writerService;

    public WriterServiceTest() {
        // Mockito anotasyonlarının başlatılması
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll() {
        // Arrange
        Writer writer = new Writer();
        writer.setName("John");
        writer.setSurname("Doe");

        // WriterRepository.findAll() metodunu mockla ve tek bir Writer döndür
        when(writerRepository.findAll()).thenReturn(Collections.singletonList(writer));

        // Act
        List<GetAllWriterResponse> result = writerService.getAll();

        // Assert
        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getName());
        assertEquals("Doe", result.get(0).getSurname());
        verify(writerRepository, times(1)).findAll();
    }

    @Test
    void testCreateWithBooks() {
        // Arrange
        CreateWriterRequest request = new CreateWriterRequest();
        request.setName("Jane");
        request.setSurname("Doe");

        List<CreateWriterRequest.BookRequest> bookRequests = new ArrayList<>();
        CreateWriterRequest.BookRequest book1 = new CreateWriterRequest.BookRequest();
        book1.setTitle("Book One");
        bookRequests.add(book1);

        CreateWriterRequest.BookRequest book2 = new CreateWriterRequest.BookRequest();
        book2.setTitle("Book Two");
        bookRequests.add(book2);

        request.setBooks(bookRequests);

        Writer writer = new Writer();
        writer.setName("Jane");
        writer.setSurname("Doe");

        List<Book> books = new ArrayList<>();
        Book book1Entity = new Book();
        book1Entity.setTitle("Book One");
        books.add(book1Entity);

        Book book2Entity = new Book();
        book2Entity.setTitle("Book Two");
        books.add(book2Entity);

        writer.setBooks(books);

        when(writerRepository.save(any(Writer.class))).thenReturn(writer);
        when(bookRepository.saveAll(anyList())).thenReturn(books);

        // Act
        Writer result = writerService.create(request);

        // Assert
        assertEquals("Jane", result.getName());
        assertEquals("Doe", result.getSurname());
        assertEquals(2, result.getBooks().size());

        verify(writerRepository, times(1)).save(any(Writer.class));
        verify(bookRepository, times(1)).saveAll(anyList());
    }

}
