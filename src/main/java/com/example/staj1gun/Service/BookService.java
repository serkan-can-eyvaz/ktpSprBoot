package com.example.staj1gun.Service;

import com.example.staj1gun.DAO.BookRepository;
import com.example.staj1gun.Entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements IBookService {

    private final BookRepository kitapDAO;

    public BookService(BookRepository kitapDAO) {
        this.kitapDAO = kitapDAO;
    }


    @Override
    public Book create(Book request) {
        Book book1 = new Book();
        book1.setTitle(request.getTitle());
        return kitapDAO.save(book1);
    }

    @Override
    public List<Book> getAll() {
        return kitapDAO.findAll();
    }
}
