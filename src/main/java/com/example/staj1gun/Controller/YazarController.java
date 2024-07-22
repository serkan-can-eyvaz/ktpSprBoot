package com.example.staj1gun.Controller;

import com.example.staj1gun.Entity.Yazar;
import com.example.staj1gun.Service.YazarService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Yazar")
public class YazarController {

    private final YazarService yazarService;

    public YazarController(YazarService yazarService) {
        this.yazarService = yazarService;
    }

    @GetMapping("/listeleme")
    public List<Yazar> listeleme() {
       return yazarService.yazargetAll();
    }

    @PostMapping("/save")
    public Yazar save(@RequestBody Yazar yazar) {
        return yazarService.createYazar(yazar);
    }
}
