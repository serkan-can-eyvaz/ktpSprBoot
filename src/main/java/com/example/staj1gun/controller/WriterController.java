package com.example.staj1gun.controller;

import com.example.staj1gun.dao.WriterRepository;
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
import java.util.Optional;

@RestController
@RequestMapping("/writers")
public class WriterController {

    private final WriterService writerService;
    private final WriterRepository writerRepository;

    public WriterController(WriterService writerService, WriterRepository writerRepository) {
        this.writerService = writerService;
        this.writerRepository = writerRepository;
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

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<WriterResponse>> getWriter(@PathVariable int id) throws WriterNotFoundException {
        Optional<Writer> writer = writerRepository.findById(id);
        if (writer.isEmpty()) {
            throw new WriterNotFoundException("Writer not found with id: " + id);
        }

        // List<WriterResponse> döndüren metodu çağırıyoruz
        List<WriterResponse> writerResponses = writerService.getById(id);

        // Bu listeyi ResponseEntity içerisine sarıyoruz
        return ResponseEntity.ok(writerResponses);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteWriter(@PathVariable int id) throws WriterNotFoundException {

        Optional<Writer> writer = writerRepository.findById(id);
        if (writer.isEmpty()) {
            throw new WriterNotFoundException("Writer not found with id: " + id);
        }
            writerService.deleteById(id);
            return ResponseEntity.noContent().build();

    }

}
