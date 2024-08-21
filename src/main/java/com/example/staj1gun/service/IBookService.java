package com.example.staj1gun.service;

import com.example.staj1gun.dto.request.CreateBookRequest;
import com.example.staj1gun.dto.response.GetAllBookResponse;
import com.example.staj1gun.dto.response.GetByIdBookResponse;
import com.example.staj1gun.entity.Book;
import com.example.staj1gun.exception.BookNotFoundException;
import com.example.staj1gun.exception.WriterNotFoundException;

import java.util.List;

public interface IBookService {

    Book create(CreateBookRequest createBookRequest) throws BookNotFoundException, WriterNotFoundException;

    List<GetAllBookResponse> getAll();

    GetByIdBookResponse getById(int id) throws BookNotFoundException;
    void deleteById(int id) throws BookNotFoundException;

}
