package com.example.staj1gun.unitTest.service;

import com.example.staj1gun.dao.WriterRepository;
import com.example.staj1gun.dto.request.CreateWriterRequest;
import com.example.staj1gun.dto.response.BookResponse;
import com.example.staj1gun.dto.response.WriterResponse;
import com.example.staj1gun.entity.Book;
import com.example.staj1gun.entity.Writer;
import com.example.staj1gun.service.WriterService;
import jakarta.persistence.EntityNotFoundException;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WriterServiceTest {

    @Mock
    private WriterRepository writerRepository;

    @InjectMocks
    private WriterService writerService;

    @Test
    void testCreateWriter_WithBooks() {
        // Arrange
        CreateWriterRequest request = new CreateWriterRequest();
        request.setName("John");
        request.setSurname("Doe");

        List<CreateWriterRequest.BookRequest> bookRequests = new ArrayList<>();
        CreateWriterRequest.BookRequest bookRequest1 = new CreateWriterRequest.BookRequest();
        bookRequest1.setTitle("Book 1");
        bookRequests.add(bookRequest1);

        CreateWriterRequest.BookRequest bookRequest2 = new CreateWriterRequest.BookRequest();
        bookRequest2.setTitle("Book 2");
        bookRequests.add(bookRequest2);

        request.setBooks(bookRequests);

        Writer writer = new Writer();
        writer.setName(request.getName());
        writer.setSurname(request.getSurname());
        writer.addBooks(List.of("Book 1", "Book 2"));

        when(writerRepository.save(Mockito.any(Writer.class))).thenReturn(writer);
        //when(writerRepository.findById(anyInt())).thenAnswer(() => );

        // Act
        Writer createdWriter = writerService.create(request);

        // Assert
        assertNotNull(createdWriter, "Created writer should not be null");
        assertEquals("John", createdWriter.getName());
        assertEquals("Doe", createdWriter.getSurname());
        assertNotNull(createdWriter.getBooks(), "Books should not be null");
        assertEquals(2, createdWriter.getBooks().size());
        assertEquals("Book 1", createdWriter.getBooks().get(0).getTitle());
        assertEquals("Book 2", createdWriter.getBooks().get(1).getTitle());
        verify(writerRepository, times(1)).save(any(Writer.class));
    }

    @Test
    void testGetById_WriterExists() {
        // Arrange
        Writer writer = new Writer();
        writer.setId(1);
        writer.setName("John");
        writer.setSurname("Doe");

        // Kitapları ekleyin
        Book book1 = new Book();
        book1.setTitle("Book 1");
        Book book2 = new Book();
        book2.setTitle("Book 2");
        writer.setBooks(List.of(book1, book2));

        // Mocklama
        when(writerRepository.findById(1)).thenReturn(Optional.of(writer));

        // Act
        List<WriterResponse> writerResponses = writerService.getById(1);

        // Assert
        assertNotNull(writerResponses, "WriterResponses should not be null");
        assertEquals(1, writerResponses.size(), "WriterResponses list should have one item");

        WriterResponse response = writerResponses.get(0);
        assertEquals("John", response.getName(), "Writer name should be John");
        assertEquals("Doe", response.getSurname(), "Writer surname should be Doe");

        List<BookResponse> books = response.getBookResponses();
        assertNotNull(books, "Books should not be null");
        assertEquals(2, books.size(), "Books list should have two items");
        assertEquals("Book 1", books.get(0).getTitle(), "First book title should be Book 1");
        assertEquals("Book 2", books.get(1).getTitle(), "Second book title should be Book 2");

        verify(writerRepository, times(1)).findById(1);
    }

    @Test
    void testGetById_WriterNotFound() {
        when(writerRepository.findById(anyInt())).thenReturn(Optional.empty());

        // Testin bir EntityNotFoundException fırlatmasını bekliyoruz
        assertThrows(EntityNotFoundException.class, () -> writerService.getById(1));
        verify(writerRepository, times(1)).findById(1);
    }

    @Test
    void testDeleteById_WriterExists() {
        Writer writer = new Writer();
        writer.setId(1);

        when(writerRepository.findById(anyInt())).thenReturn(Optional.of(writer));
        doNothing().when(writerRepository).delete(writer);

        // Testi çalıştır
        assertDoesNotThrow(() -> writerService.deleteById(1));

        // Doğrulamalar
        verify(writerRepository, times(1)).findById(1);
        verify(writerRepository, times(1)).delete(writer);
    }

    @Test
    void testDeleteById_WriterNotFound() {
        when(writerRepository.findById(anyInt())).thenReturn(Optional.empty());

        // Testin bir EntityNotFoundException fırlatmasını bekliyoruz
        assertThrows(EntityNotFoundException.class, () -> writerService.deleteById(1));
        verify(writerRepository, times(1)).findById(1);
    }
}
