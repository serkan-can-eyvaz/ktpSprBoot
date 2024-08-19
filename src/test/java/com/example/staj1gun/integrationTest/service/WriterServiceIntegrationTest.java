package com.example.staj1gun.integrationTest.service;

import com.example.staj1gun.dao.WriterRepository;
import com.example.staj1gun.dto.request.CreateWriterRequest;
import com.example.staj1gun.dto.response.GetAllWriterResponse;
import com.example.staj1gun.dto.response.WriterResponse;
import com.example.staj1gun.entity.Writer;
import com.example.staj1gun.service.WriterService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Transactional
public class WriterServiceIntegrationTest {

    @Autowired
    private WriterService writerService;

    @Autowired
    private WriterRepository writerRepository;

    @BeforeEach
    void setUp() {
        writerRepository.deleteAll();
        Writer writer1 = new Writer();
        writer1.setName("John");
        writer1.setSurname("Doe");
        writerRepository.save(writer1);

        Writer writer2 = new Writer();
        writer2.setName("Jane");
        writer2.setSurname("Doe");
        writerRepository.save(writer2);
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

        // Act
        Writer createdWriter = writerService.create(request);

        // Assert
        assertNotNull(createdWriter, "Created writer should not be null");
        assertEquals("John", createdWriter.getName(), "Writer name should match");
        assertEquals("Doe", createdWriter.getSurname(), "Writer surname should match");
        assertNotNull(createdWriter.getBooks(), "Books should not be null");
        assertEquals(2, createdWriter.getBooks().size(), "There should be 2 books associated with the writer");

        // Veritabanından veri alarak kontrol etme
        List<Writer> writers = writerRepository.findAll();
        assertFalse(writers.isEmpty(), "Writer list should not be empty");
        Writer dbWriter = writers.get(0);
        assertEquals("John", dbWriter.getName(), "Writer name in database should match");
        assertEquals("Doe", dbWriter.getSurname(), "Writer surname in database should match");
        assertNotNull(dbWriter.getBooks(), "Books in database should not be null");
    }

    @Test
    void GetAll_Success() {
        List<GetAllWriterResponse> writers = writerService.getAll();

        assertFalse(writers.isEmpty(), "The list of writers should not be empty");
        assertEquals(2, writers.size(), "There should be 2 writers in the list");

        GetAllWriterResponse response1 = writers.get(0);
        GetAllWriterResponse response2 = writers.get(1);

        assertNotNull(response1.getId(), "Writer ID should not be null");
        assertNotNull(response1.getName(), "Writer name should not be null");
        assertNotNull(response1.getSurname(), "Writer surname should not be null");

        assertNotNull(response2.getId(), "Writer ID should not be null");
        assertNotNull(response2.getName(), "Writer name should not be null");
        assertNotNull(response2.getSurname(), "Writer surname should not be null");
    }

    @Test
    void GetById_Success() {
        // Arrange
        CreateWriterRequest createWriterRequest = new CreateWriterRequest();
        createWriterRequest.setName("Jane");
        createWriterRequest.setSurname("Doe");
        Writer createdWriter = writerService.create(createWriterRequest);

        // Act
        List<WriterResponse> writerResponses = writerService.getById(createdWriter.getId());

        // Assert
        assertNotNull(writerResponses, "Response list should not be null");
        assertEquals(1, writerResponses.size(), "There should be exactly one writer response");

        WriterResponse writerResponse = writerResponses.get(0);
        assertNotNull(writerResponse, "Writer response should not be null");
        assertEquals("Jane", writerResponse.getName(), "Writer name should match");
        assertEquals("Doe", writerResponse.getSurname(), "Writer surname should match");
    }


    @Test
    void DeleteById_Success() {
        CreateWriterRequest createWriterRequest = new CreateWriterRequest();
        createWriterRequest.setName("Jane");
        createWriterRequest.setSurname("Doe");
        Writer createdWriter = writerService.create(createWriterRequest);

        writerService.deleteById(createdWriter.getId());

        assertThrows(EntityNotFoundException.class, () -> writerService.getById(createdWriter.getId()), "Writer should be deleted");
    }
}
