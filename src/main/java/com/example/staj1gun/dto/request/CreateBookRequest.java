package com.example.staj1gun.dto.request;

public class CreateBookRequest
{
    private String title;//null olmamalı
    private int writerId;

    public int getWriterId() {
        return writerId;
    }

    public void setWriterId(int writerId) {
        this.writerId = writerId;
    }

    public CreateBookRequest() {
    }

    public CreateBookRequest(String title, int writerId) {
        this.title = title;
        this.writerId = writerId;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
