package com.example.staj1gun.dto.mapper;
import com.example.staj1gun.dto.request.CreateBookRequest;
import com.example.staj1gun.dto.response.getAllBookResponse;
import com.example.staj1gun.dto.response.getByIdBookResponse;
import com.example.staj1gun.entity.Book;
import com.example.staj1gun.entity.Writer;
import org.springframework.stereotype.Component;
@Component
public class BookMapper {
    public Book toBook(CreateBookRequest createBookRequest, Writer writer) {
        Book book = new Book();
        book.setTitle(createBookRequest.getTitle());
        book.setWriter(writer);
        return book;
    }

    public getAllBookResponse toGetAllBookResponse(Book book) {
        getAllBookResponse response = new getAllBookResponse();
        response.setId(book.getId());
        response.setTitle(book.getTitle());
        Writer writer = book.getWriter();
        if (writer != null) {
            response.setWriterName(writer.getName());
            response.setWriterSurname(writer.getSurname());
        } else {
            response.setWriterName("Bilinmiyor");
            response.setWriterSurname("");
        }
        return response;
    }

    public getByIdBookResponse toGetByIdBookResponse(Book book) {
        getByIdBookResponse response = new getByIdBookResponse();
        response.setId(book.getId());
        response.setTitle(book.getTitle());
        Writer writer = book.getWriter();
        if (writer != null) {
            response.setWriterName(writer.getName());
            response.setWriterSurname(writer.getSurname());
        } else {
            response.setWriterName("Bilinmiyor");
            response.setWriterSurname("");
        }
        return response;
    }
    }


