package com.example.staj1gun.controller;

import com.example.staj1gun.dto.request.CreateWriterRequest;
import com.example.staj1gun.dto.response.WriterResponse;
import com.example.staj1gun.dto.response.GetAllWriterResponse;
import com.example.staj1gun.entity.Writer;
import com.example.staj1gun.service.WriterService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/writers")
public class WriterController {

    private final WriterService writerService;

    public WriterController(WriterService writerService) {
        this.writerService = writerService;
    }

    @GetMapping()
    public List<GetAllWriterResponse> listeleme() {
        return writerService.getAll();
    }

    @PostMapping
    public ResponseEntity<Writer> createWriter(@RequestBody CreateWriterRequest createWriterRequest) {
        Writer writer = writerService.create(createWriterRequest);
        return ResponseEntity.ok(writer);
    }

    @GetMapping("/{id}")
    public List<WriterResponse> getWriter(@PathVariable int id) {
        List<WriterResponse> writerResponses = writerService.getById(id);
        if (writerResponses.isEmpty()) {
            throw new EntityNotFoundException("Yazar bulunamadı, id: " + id);
        }
        return writerResponses;
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<WriterResponse> deleteWriter(@PathVariable int id) {
        writerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
