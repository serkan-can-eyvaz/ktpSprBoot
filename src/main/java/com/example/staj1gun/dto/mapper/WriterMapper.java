package com.example.staj1gun.dto.mapper;

import com.example.staj1gun.dto.response.BookResponse;
import com.example.staj1gun.dto.response.WriterResponse;
import com.example.staj1gun.dto.response.getAllWriterResponse;
import com.example.staj1gun.entity.Book;
import com.example.staj1gun.entity.Writer;

import java.util.ArrayList;
import java.util.List;

    public class WriterMapper {

        public static WriterResponse toWriterResponse(Writer writer) {
            WriterResponse response = new WriterResponse();
            response.setName(writer.getName());
            response.setSurname(writer.getSurname());
            response.setBookResponses(toBookResponseList(writer.getBooks()));
            return response;
        }

        private static List<BookResponse> toBookResponseList(List<Book> books) {
            //stream
            List<BookResponse> bookResponses = new ArrayList<>();
            for (Book book : books) {
                BookResponse bookResponse = new BookResponse();
                bookResponse.setTitle(book.getTitle());
                bookResponses.add(bookResponse);
            }
            return bookResponses;
        }

        public static List<WriterResponse> toWriterResponseList(List<Writer> writers) {
            List<WriterResponse> writerResponses = new ArrayList<>();
            for (Writer writer : writers) {
                writerResponses.add(toWriterResponse(writer));
            }
            return writerResponses;
        }

        public static getAllWriterResponse toGetAllWriterResponse(Writer writer) {
            getAllWriterResponse response = new getAllWriterResponse();
            response.setId(writer.getId());
            response.setName(writer.getName());
            response.setSurname(writer.getSurname());
            return response;
        }

        public static List<getAllWriterResponse> toGetAllWriterResponseList(List<Writer> writers) {
            List<getAllWriterResponse> responses = new ArrayList<>();
            for (Writer writer : writers) {
                responses.add(toGetAllWriterResponse(writer));
            }
            return responses;
        }
}
