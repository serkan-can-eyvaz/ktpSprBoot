package com.example.staj1gun.dto.response;

import java.util.List;

public class WriterResponse {
    private String name;
    private String surname;
    private List<BookResponse> bookResponses;

    public WriterResponse() {
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

    public List<BookResponse> getBookResponses() {
        return bookResponses;
    }

    public void setBookResponses(List<BookResponse> bookResponses) {
        this.bookResponses = bookResponses;
    }
}

