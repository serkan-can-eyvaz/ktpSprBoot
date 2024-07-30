package com.example.staj1gun.service;

import com.example.staj1gun.dao.BookRepository;
import com.example.staj1gun.dao.WriterRepository;
import com.example.staj1gun.dto.request.CreateBookRequest;
import com.example.staj1gun.dto.response.getAllBookResponse;
import com.example.staj1gun.dto.response.getByIdBookResponse;
import com.example.staj1gun.entity.Book;
import com.example.staj1gun.entity.Writer;
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
        List<getAllBookResponse> getAllBookResponse = new ArrayList<>();//stream bak
        for (Book book : books) {
            getAllBookResponse responseItem = new getAllBookResponse();
            responseItem.setId(book.getId());
            responseItem.setTitle(book.getTitle());

            // Yazar bilgisini kitaptan al
            Writer writer = book.getWriter(); // Kitap nesnesi üzerinden yazarı al
            if (writer != null) {
                responseItem.setWriterName(writer.getName());
                responseItem.setWriterSurname(writer.getSurname());
            }
            /*else {
                responseItem.setWriterName("Unknown");
                responseItem.setWriterSurname("");
            }*/
            getAllBookResponse.add(responseItem);
        }
        return getAllBookResponse;
    }

    @Override
    public getByIdBookResponse  getById(int id) {
        return bookRepository.findById(id)
                .map(book -> {
                    Writer writer = book.getWriter();
                    getByIdBookResponse responseItem = new getByIdBookResponse();
                    responseItem.setId(book.getId());
                    responseItem.setTitle(book.getTitle());
                    responseItem.setWriterName(writer.getName());
                    responseItem.setWriterSurname(writer.getSurname());
                    return responseItem;
                })
                .orElseThrow(() -> new RuntimeException("Kitap bulunamadı"));
        /*Book book = bookRepository.findById(id).get();
        Writer writer = book.getWriter();
        getByIdBookResponse responseItem = new getByIdBookResponse();
        responseItem.setId(book.getId());
        responseItem.setTitle(book.getTitle());
        responseItem.setWriterName(writer.getName());
        responseItem.setWriterSurname(writer.getSurname());
        return responseItem;*/
    }

    @Override
    public void deleteById(int id) {
       Book book =bookRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Book not found with id: " + id));
       bookRepository.delete(book);
    }





}

