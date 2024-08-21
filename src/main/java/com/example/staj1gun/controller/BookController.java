package com.example.staj1gun.controller;

import com.example.staj1gun.dto.request.CreateBookRequest;
import com.example.staj1gun.dto.response.GetAllBookResponse;
import com.example.staj1gun.dto.response.GetByIdBookResponse;
import com.example.staj1gun.entity.Book;
import com.example.staj1gun.exception.BookNotFoundException;
import com.example.staj1gun.exception.WriterNotFoundException;
import com.example.staj1gun.service.BookService;
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
    public List<GetAllBookResponse> getAll() {
        return bookService.getAll();
    }

    @PostMapping()
    public Book addBook(@RequestBody CreateBookRequest createBookRequest) throws WriterNotFoundException {
        return bookService.create(createBookRequest);

    }

    @GetMapping("/{id}")
    public GetByIdBookResponse getBook(@PathVariable int id) throws BookNotFoundException {
        return bookService.getById(id);
    }
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable int id) throws BookNotFoundException {
        bookService.deleteById(id);
    }


}
