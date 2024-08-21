package com.example.staj1gun.controller;

import com.example.staj1gun.dao.BookRepository;
import com.example.staj1gun.dto.request.CreateBookRequest;
import com.example.staj1gun.dto.response.GetAllBookResponse;
import com.example.staj1gun.dto.response.GetByIdBookResponse;
import com.example.staj1gun.entity.Book;
import com.example.staj1gun.exception.BookNotFoundException;
import com.example.staj1gun.exception.WriterNotFoundException;
import com.example.staj1gun.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final BookRepository bookRepository;

    public BookController(BookService bookService, BookRepository bookRepository) {
        this.bookService = bookService;
        this.bookRepository = bookRepository;
    }

    @GetMapping()
    public List<GetAllBookResponse> getAll() {
        return bookService.getAll();
    }

    @PostMapping()
    public Book addBook(@RequestBody CreateBookRequest createBookRequest) throws WriterNotFoundException {
        return bookService.create(createBookRequest);

    }

    @RequestMapping(value = "/{id}", method = GET)
    public GetByIdBookResponse getBook(@PathVariable int id) throws BookNotFoundException {
        Optional<Book> book =bookRepository.findById(id);
        if (book == null)  throw new BookNotFoundException(id);

        return bookService.getById(id);

    }
    /*@RequestMapping(value="/orders/{id}", method=GET)
    public String showOrder(@PathVariable("id") long id, Model model) {
        Order order = orderRepository.findOrderById(id);

        if (order == null) throw new OrderNotFoundException(id);

        model.addAttribute(order);
        return "orderDetail";
    }*/
    @RequestMapping(value = "/{id}",method = DELETE)
    public void deleteBook(@PathVariable int id) throws BookNotFoundException {
        Optional<Book> book = bookRepository.findById(id);
        if (book == null)  throw new BookNotFoundException(id);
        bookService.deleteById(id);
    }


}
