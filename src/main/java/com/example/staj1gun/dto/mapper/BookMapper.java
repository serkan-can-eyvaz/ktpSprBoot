package com.example.staj1gun.dto.mapper;
import com.example.staj1gun.dto.request.CreateBookRequest;
import com.example.staj1gun.dto.response.GetAllBookResponse;
import com.example.staj1gun.dto.response.GetByIdBookResponse;
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

    public GetAllBookResponse toGetAllBookResponse(Book book) {
        GetAllBookResponse response = new GetAllBookResponse();
        response.setId(book.getId());
        response.setTitle(book.getTitle());
        Writer writer = book.getWriter();
        if (writer != null) {
            response.setWriterName(writer.getName());
            response.setWriterSurname(writer.getSurname());
        } else {
            response.setWriterName("Unknown");
            response.setWriterSurname("");
        }
        return response;
    }

    public GetByIdBookResponse toGetByIdBookResponse(Book book) {
        GetByIdBookResponse response = new GetByIdBookResponse();
        response.setId(book.getId());
        response.setTitle(book.getTitle());
        Writer writer = book.getWriter();
        if (writer != null) {
            response.setWriterName(writer.getName());
            response.setWriterSurname(writer.getSurname());
        } else {
            response.setWriterName("Unknown");
            response.setWriterSurname("");
        }
        return response;
    }
    }


