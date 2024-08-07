package com.example.staj1gun.service;

import com.example.staj1gun.dao.WriterRepository;
import com.example.staj1gun.dto.response.GetAllWriterResponse;
import com.example.staj1gun.entity.Writer;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class WriterServiceTest {

    @Mock
    private WriterRepository writerRepository;

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
}
