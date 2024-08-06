package com.example.staj1gun.dto.response;

public class GetByIdBookResponse {
    private long id;
    private String title;
    private String writerName;
    private String writerSurname;

    public GetByIdBookResponse() {
    }
    //entegrasyon ve birim testi nasıl yazılır
    public GetByIdBookResponse(long id, String title, String writerName, String writerSurname) {
        this.id = id;
        this.title = title;
        this.writerName = writerName;
        this.writerSurname = writerSurname;
    }

    public String getWriterName() {
        return writerName;
    }

    public void setWriterName(String writerName) {
        this.writerName = writerName;
    }

    public String getWriterSurname() {
        return writerSurname;
    }

    public void setWriterSurname(String writerSurname) {
        this.writerSurname = writerSurname;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
