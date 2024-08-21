package com.example.staj1gun.service;

import com.example.staj1gun.dao.BookRepository;
import com.example.staj1gun.dao.WriterRepository;
import com.example.staj1gun.dto.mapper.BookMapper;
import com.example.staj1gun.dto.request.CreateBookRequest;
import com.example.staj1gun.dto.response.GetAllBookResponse;
import com.example.staj1gun.dto.response.GetByIdBookResponse;
import com.example.staj1gun.entity.Book;
import com.example.staj1gun.entity.Writer;
import com.example.staj1gun.exception.BookNotFoundException;
import com.example.staj1gun.exception.WriterNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService implements IBookService {

    private final BookRepository bookRepository;
    private final WriterRepository writerRepository;
    private final BookMapper bookMapper;

    public BookService(BookRepository bookRepository, WriterRepository writerRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.writerRepository = writerRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public Book create(CreateBookRequest createBookRequest) throws WriterNotFoundException {
        Optional<Writer> writerOptional = writerRepository.findById(createBookRequest.getWriterId());
        if (!writerOptional.isPresent()) {
            throw new WriterNotFoundException("Writer id not found: " + createBookRequest.getWriterId());
        }
        Writer writer = writerOptional.get();
        Book book = bookMapper.toBook(createBookRequest, writer);
        System.out.println("Mapped Book: " + book);
        return bookRepository.save(book);
    }

    @Override
    public List<GetAllBookResponse> getAll() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(bookMapper::toGetAllBookResponse)
                .collect(Collectors.toList());
    }
    //daha mantıklı açıklayıcı cümlelerle isimlendirmeleri düzelt
    @Override
    public GetByIdBookResponse getById(int id) throws BookNotFoundException {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found" ));
        return bookMapper.toGetByIdBookResponse(book);
    }

    @Override
    public void deleteById(int id) throws BookNotFoundException {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found" ));
        bookRepository.delete(book);
    }
}
