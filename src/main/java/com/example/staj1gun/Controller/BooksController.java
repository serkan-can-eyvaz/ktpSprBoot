package com.example.staj1gun.Controller;

import com.example.staj1gun.Entity.Book;
import com.example.staj1gun.Service.BookService;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Books")
public class BooksController {

    private final BookService bookService;

    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping()
    public List<Book> getAll() {
        return bookService.getAll();
    }

    @PostMapping()
    public Book create(@RequestParam String title,
                     @RequestParam String writerName,
                     @RequestParam String writerSurname) {

        return bookService.create(title, writerName, writerSurname);
    }
}
