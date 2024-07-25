package com.example.staj1gun.Service;

import com.example.staj1gun.DAO.BookRepository;
import com.example.staj1gun.DAO.WriterRepository;
import com.example.staj1gun.DTO.Request.CreateBookRequest;
import com.example.staj1gun.DTO.Response.getAllBookResponse;
import com.example.staj1gun.DTO.Response.getByIdBookResponse;
import com.example.staj1gun.Entity.Book;
import com.example.staj1gun.Entity.Writer;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService {

    private final BookRepository bookRepository;
    private  final WriterRepository writerRepository;

    public BookService(BookRepository bookRepository, WriterRepository writerRepository) {
        this.bookRepository = bookRepository;
        this.writerRepository = writerRepository;
    }


    @Override
    public Book create(CreateBookRequest createBookRequest) {
        Optional<Writer> byId = writerRepository.findById(createBookRequest.getWriterId());
        if (!byId.isPresent()) {
            //NoSuchElementException boş olması durumda fırlatır
            throw new EntityNotFoundException("Writer not found with id: " + createBookRequest.getWriterId());
        }
        Writer writer = byId.get();
        Book book = new Book();
        book.setWriter(writer);
        book.setTitle(createBookRequest.getTitle());

        return bookRepository.save(book);
    }

    @Override
    public List<getAllBookResponse> getAll() {
        List<Book>books = bookRepository.findAll();
        List<getAllBookResponse> getAllBookResponse = new ArrayList<>();//stream yöntemine bak
        for (Book book : books) {
           getAllBookResponse responseItem = new getAllBookResponse();
            responseItem.setId(book.getId());
            responseItem.setTitle(book.getTitle());
            responseItem.setWriterName(book.getWriterName());
            responseItem.setWriterSurname(book.getWriterSurname());
            getAllBookResponse.add(responseItem);
        }
        return getAllBookResponse;
    }

    @Override
    public getByIdBookResponse  getById(int id) {

        Book book = bookRepository.findById(id).get();
        getByIdBookResponse responseItem = new getByIdBookResponse();
        responseItem.setId(book.getId());
        responseItem.setTitle(book.getTitle());
        responseItem.setWriterName(book.getWriterName());
        responseItem.setWriterSurname(book.getWriterSurname());
        return responseItem;

    }
}
