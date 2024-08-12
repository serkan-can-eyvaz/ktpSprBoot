package com.example.staj1gun.dto.request;

import java.util.ArrayList;
import java.util.List;

public class CreateWriterRequest {
    private String name;
    private String surname;
    private List<BookRequest> books = new ArrayList<BookRequest>();

    public CreateWriterRequest() {}

    public CreateWriterRequest(String name, String surname, List<BookRequest> books) {
        this.name = name;
        this.surname = surname;
        this.books = books;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BookRequest> getBooks() {
        return books;
    }

    public void setBooks(List<BookRequest> books) {
        this.books = books;
    }
    //gereksiz
    public static class BookRequest {
        private String title;

        public BookRequest() {}

        public BookRequest(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
