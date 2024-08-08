package com.example.staj1gun.service;

import com.example.staj1gun.dao.WriterRepository;
import com.example.staj1gun.dto.request.CreateWriterRequest;
import com.example.staj1gun.entity.Writer;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class WriterServiceTest {

    @Mock
    private WriterRepository writerRepository;

    @InjectMocks
    private WriterService writerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

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

        Mockito.when(writerRepository.save(any(Writer.class))).thenReturn(writer);

        // Act
        Writer createdWriter = writerService.create(request);

        System.out.println("Writer to save: " + writer);
        System.out.println("Saved writer: " + writerRepository.save(writer));
        System.out.println("Created writer: " + createdWriter);

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
        Writer writer = new Writer();
        writer.setId(1);
        writer.setName("John");
        writer.setSurname("Doe");

        when(writerRepository.findById(anyInt())).thenReturn(Optional.of(writer));

        var writerResponse = writerService.getById(1);

        assertNotNull(writerResponse);
        assertEquals(1, writerResponse.size());
        assertEquals("John", writerResponse.get(0).getName());
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
