package com.example.staj1gun.Service;

import com.example.staj1gun.DTO.Request.CreateWriterRequest;
import com.example.staj1gun.DTO.Response.WriterResponse;
import com.example.staj1gun.DTO.Response.getAllWriterResponse;
import com.example.staj1gun.DTO.Response.getByIdBookResponse;
import com.example.staj1gun.Entity.Writer;

import java.util.List;

public interface IWriterService {
    List<getAllWriterResponse> getAll();
    Writer create(CreateWriterRequest createWriterRequest);
    List<WriterResponse> getById(int id);
}
