package com.example.staj1gun.Service;

import com.example.staj1gun.Entity.Book;

import java.util.List;

public interface IBookService {

    Book create(String title, String writerName, String writerSurname);

    List<Book> getAll();
}
