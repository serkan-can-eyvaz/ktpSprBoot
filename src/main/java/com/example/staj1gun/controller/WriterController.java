package com.example.staj1gun.controller;

import com.example.staj1gun.dto.request.CreateWriterRequest;
import com.example.staj1gun.dto.response.WriterResponse;
import com.example.staj1gun.dto.response.GetAllWriterResponse;
import com.example.staj1gun.entity.Writer;
import com.example.staj1gun.exception.WriterNotFoundException;
import com.example.staj1gun.service.WriterService;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<WriterResponse>> getWriter(@PathVariable int id) {
        try {
            return ResponseEntity.ok(writerService.getById(id));
        } catch (WriterNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWriter(@PathVariable int id) {
        try {
            writerService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (WriterNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
