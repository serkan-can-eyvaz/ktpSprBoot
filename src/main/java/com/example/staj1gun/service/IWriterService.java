package com.example.staj1gun.service;

import com.example.staj1gun.dto.request.CreateWriterRequest;
import com.example.staj1gun.dto.response.WriterResponse;
import com.example.staj1gun.dto.response.getAllWriterResponse;
import com.example.staj1gun.entity.Writer;

import java.util.List;

public interface IWriterService {
    List<getAllWriterResponse> getAll();
    Writer create(CreateWriterRequest createWriterRequest);
    List<WriterResponse> getById(int id);
    void deleteById(int id);
}
