package com.example.staj1gun.Controller;

import com.example.staj1gun.DTO.Request.CreateWriterRequest;
import com.example.staj1gun.DTO.Response.WriterResponse;
import com.example.staj1gun.DTO.Response.getAllWriterResponse;
import com.example.staj1gun.Entity.Writer;
import com.example.staj1gun.Service.WriterService;
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
    public List<getAllWriterResponse> listeleme() {
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
