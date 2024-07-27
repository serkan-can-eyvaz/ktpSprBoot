package com.example.staj1gun.DTO.Response;

import java.util.List;

public class BookResponse {
    private String title;
    private List<BookResponse>bookResponses;

    public BookResponse() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<BookResponse> getBookResponses() {
        return bookResponses;
    }

    public void setBookResponses(List<BookResponse> bookResponses) {
        this.bookResponses = bookResponses;
    }
}
