package com.example.staj1gun.Service;

import com.example.staj1gun.DAO.WriterRepository;
import com.example.staj1gun.DTO.Request.CreateWriterRequest;
import com.example.staj1gun.DTO.Response.getAllWriterResponse;
import com.example.staj1gun.Entity.Writer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class WriterService implements IWriterService {

    private final WriterRepository writerRepository;

    public WriterService(WriterRepository writerRepository) {
        this.writerRepository = writerRepository;
    }

    @Override
    public List<getAllWriterResponse> getAll() {
        List<Writer> writers = writerRepository.findAll();
        List<getAllWriterResponse> brandresponses = new ArrayList<>();
        for (Writer writer : writers) {
            getAllWriterResponse responseItem = new getAllWriterResponse();
            responseItem.setId(writer.getId());
            responseItem.setName(writer.getName());
            responseItem.setSurname(writer.getSurname());
            brandresponses.add(responseItem);
        }
        return brandresponses;
    }

    @Override
    public Writer create(CreateWriterRequest createWriterRequest) {
        Writer writer = new Writer();
        writer.setName(createWriterRequest.getName());
        writer.setSurname(createWriterRequest.getSurname());
        return writerRepository.save(writer);
    }

}
