package com.example.staj1gun.Service;

import com.example.staj1gun.dto.request.CreateBookRequest;
import com.example.staj1gun.dto.response.getAllBookResponse;
import com.example.staj1gun.dto.response.getByIdBookResponse;
import com.example.staj1gun.Entity.Book;

import java.util.List;

public interface IBookService {

    Book create(CreateBookRequest createBookRequest);

    List<getAllBookResponse> getAll();

    getByIdBookResponse getById(int id);
    void deleteById(int id);

}
