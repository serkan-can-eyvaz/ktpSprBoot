package com.example.staj1gun.service;

import com.example.staj1gun.dto.request.CreateWriterRequest;
import com.example.staj1gun.dto.response.WriterResponse;
import com.example.staj1gun.dto.response.GetAllWriterResponse;
import com.example.staj1gun.entity.Writer;
import com.example.staj1gun.exception.WriterNotFoundException;

import java.util.List;

public interface IWriterService {
    List<GetAllWriterResponse> getAll();
    Writer create(CreateWriterRequest createWriterRequest);
    List<WriterResponse> getById(int id) throws WriterNotFoundException;
    void deleteById(int id) throws WriterNotFoundException;
}
