package com.example.staj1gun.dto.request;

public class BookRequest {
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
