package com.example.staj1gun.Service;

import com.example.staj1gun.DTO.Request.CreateBookRequest;
import com.example.staj1gun.DTO.Response.getAllBookResponse;
import com.example.staj1gun.DTO.Response.getByIdBookResponse;
import com.example.staj1gun.Entity.Book;

import java.util.List;

public interface IBookService {

    Book create(CreateBookRequest createBookRequest);

    List<getAllBookResponse> getAll();

    getByIdBookResponse getById(int id);
}
