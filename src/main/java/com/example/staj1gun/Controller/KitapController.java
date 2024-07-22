package com.example.staj1gun.Controller;

import com.example.staj1gun.Entity.Kitap;
import com.example.staj1gun.Service.KitapService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Kitap")
public class KitapController {

    private final KitapService kitapService;

    public KitapController(KitapService kitapService) {
        this.kitapService = kitapService;
    }

    @GetMapping("/getAll")
    public List<Kitap> getAll() {
        return kitapService.getAllKitap();
    }

    @PostMapping("/save")
    public Kitap save(@RequestBody Kitap kitap) {
         return kitapService.createKitap(kitap);
    }
}
