package com.example.staj1gun.DTO.Response;

import com.example.staj1gun.Entity.Book;

import java.util.List;

public class WriterResponse {
    private String name;
    private String surname;
    private String title;
    private long bookId;
    private List<BookResponse> bookResponses ;

    public WriterResponse() {
    }

    public WriterResponse(String name, String surname, String title, long bookId) {
        this.name = name;
        this.surname = surname;
        this.title = title;
        this.bookId = bookId;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public List<BookResponse> getBookResponses() {
        return bookResponses;
    }

    public void setBookResponses(List<BookResponse> bookResponses) {
        this.bookResponses = bookResponses;
    }
}
