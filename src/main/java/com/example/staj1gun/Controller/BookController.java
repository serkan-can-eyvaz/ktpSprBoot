package com.example.staj1gun.Controller;

import com.example.staj1gun.DTO.Request.CreateBookRequest;
import com.example.staj1gun.DTO.Response.getAllBookResponse;
import com.example.staj1gun.DTO.Response.getByIdBookResponse;
import com.example.staj1gun.Entity.Book;
import com.example.staj1gun.Service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping()
    public List<getAllBookResponse> getAll() {
        return bookService.getAll();
    }

    @PostMapping()
    public Book addBook(@RequestBody CreateBookRequest createBookRequest) {
        return bookService.create(createBookRequest);

    }

    @GetMapping("/{id}")
    public getByIdBookResponse getBook(@PathVariable int id) {
        return bookService.getById(id);
    }

}
