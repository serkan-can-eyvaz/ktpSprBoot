package com.example.staj1gun.Controller;

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
    public List<Writer> listeleme() {
       return writerService.getAll();
    }

    @PostMapping("/save")
    public Writer save(@RequestBody Writer yazar) {
        return writerService.create(yazar);
    }
}
