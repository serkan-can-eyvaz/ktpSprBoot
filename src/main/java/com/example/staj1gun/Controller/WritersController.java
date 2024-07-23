package com.example.staj1gun.Controller;

import com.example.staj1gun.DTO.Request.CreateWriterRequest;
import com.example.staj1gun.DTO.Response.getAllWriterResponse;
import com.example.staj1gun.Entity.Writer;
import com.example.staj1gun.Service.WriterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Writers")
public class WritersController {

    private final WriterService writerService;

    public WritersController(WriterService writerService) {
        this.writerService = writerService;
    }

    @GetMapping()
    public List<getAllWriterResponse> listeleme() {
       return writerService.getAll();
    }

    @PostMapping("/save")
    public Writer save(@RequestBody CreateWriterRequest createWriterRequest) {
        return writerService.create(createWriterRequest);
    }
}
