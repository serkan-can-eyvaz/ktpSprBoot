package com.example.staj1gun.Service;

import com.example.staj1gun.DAO.BookRepository;
import com.example.staj1gun.DAO.WriterRepository;
import com.example.staj1gun.Entity.Book;
import com.example.staj1gun.Entity.Writer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements IBookService {

    private final BookRepository bookRepository;
    private final WriterRepository writerRepository;

    public BookService(BookRepository bookRepository, WriterRepository writerRepository) {
        this.bookRepository = bookRepository;
        this.writerRepository = writerRepository;
    }

    @Override
    public Book create(String title, String writerName, String writerSurname) {
        Writer writer = writerRepository.findByNameAndSurname(writerName, writerSurname);

        if (writer == null) {
            writer = new Writer();
            writer.setName(writerName);
            writer.setSurname(writerSurname);
            writerRepository.save(writer);
            System.out.println("Writer saved: " + writer.getId());
        } else {
            System.out.println("Writer already exists: " + writer.getId());
        }

        Book book = new Book();
        book.setTitle(title);
        book.setWriter(writer);

        System.out.println("Saving book with title: " + title);
        book = bookRepository.save(book);
        System.out.println("Book saved with ID: " + book.getId());

        return book;
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }
}
