package com.example.staj1gun.Service;

import com.example.staj1gun.DAO.WriterRepository;
import com.example.staj1gun.DTO.Request.CreateWriterRequest;
import com.example.staj1gun.DTO.Response.BookResponse;
import com.example.staj1gun.DTO.Response.WriterResponse;
import com.example.staj1gun.DTO.Response.getAllWriterResponse;
import com.example.staj1gun.Entity.Book;
import com.example.staj1gun.Entity.Writer;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WriterService implements IWriterService {

    private final WriterRepository writerRepository;

    public WriterService(WriterRepository writerRepository) {
        this.writerRepository = writerRepository;
    }

    @Override
    public List<getAllWriterResponse> getAll() {
        List<Writer> writers = writerRepository.findAll();
        List<getAllWriterResponse> brandresponses = new ArrayList<>();
        for (Writer writer : writers) {
            getAllWriterResponse responseItem = new getAllWriterResponse();
            responseItem.setId(writer.getId());
            responseItem.setName(writer.getName());
            responseItem.setSurname(writer.getSurname());
            brandresponses.add(responseItem);
        }
        return brandresponses;
    }

    @Override
    public Writer create(CreateWriterRequest createWriterRequest) {
        Writer writer = new Writer();
        writer.setName(createWriterRequest.getName());
        writer.setSurname(createWriterRequest.getSurname());

        List<Book> books = new ArrayList<>();
        for (CreateWriterRequest.BookRequest bookRequest : createWriterRequest.getBooks()) {
            Book book = new Book();
            book.setTitle(bookRequest.getTitle());
            book.setWriter(writer);
            books.add(book);
        }
        writer.setBooks(books);

        return writerRepository.save(writer);
    }

    @Override
    public List<WriterResponse> getById(int id) {
        List<WriterResponse> writerResponses = new ArrayList<>();
        Writer writer = writerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Yazar bulunamadı, id: " + id));

        WriterResponse responseItem = new WriterResponse();
        responseItem.setName(writer.getName());
        responseItem.setSurname(writer.getSurname());

        List<BookResponse> bookResponses = new ArrayList<>();
        for (Book book : writer.getBooks()) {
            BookResponse bookResponse = new BookResponse();
            bookResponse.setTitle(book.getTitle());
            bookResponses.add(bookResponse);
        }
        responseItem.setBookResponses(bookResponses);
        writerResponses.add(responseItem);

        return writerResponses;
    }

    @Override
    public Writer deleteById(int id) {
        Writer writer =writerRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("author not found"+id) );
        writerRepository.delete(writer);
        return null;
    }
}
