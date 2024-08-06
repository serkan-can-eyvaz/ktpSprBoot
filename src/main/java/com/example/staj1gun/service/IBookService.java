package com.example.staj1gun.service;

import com.example.staj1gun.dto.request.CreateBookRequest;
import com.example.staj1gun.dto.response.GetAllBookResponse;
import com.example.staj1gun.dto.response.GetByIdBookResponse;
import com.example.staj1gun.entity.Book;

import java.util.List;

public interface IBookService {

    Book create(CreateBookRequest createBookRequest);

    List<GetAllBookResponse> getAll();

    GetByIdBookResponse getById(int id);
    void deleteById(int id);

}
