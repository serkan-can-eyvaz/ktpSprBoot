package com.example.staj1gun.entity;

import com.example.staj1gun.dto.request.CreateWriterRequest;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Writer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;

    @OneToMany(mappedBy = "writer", cascade = CascadeType.ALL, fetch = FetchType.LAZY , orphanRemoval = true)
    @JsonManagedReference
    List<Book>books = new ArrayList<>();

    public Writer()
    {}

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void addBook(Book book) {
        books.add(book);
        book.setWriter(this);

    }
    public void addBooks(List<CreateWriterRequest.BookRequest> bookRequests) {
        for (CreateWriterRequest.BookRequest bookRequest : bookRequests) {
            Book book = new Book();
            book.setTitle(bookRequest.getTitle());
            this.addBook(book);
        }
    }


    public Writer(int id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
