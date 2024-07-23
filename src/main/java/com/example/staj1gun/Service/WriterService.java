package com.example.staj1gun.Service;

import com.example.staj1gun.DAO.WriterRepository;
import com.example.staj1gun.Entity.Writer;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WriterService implements IWriterService {

    private final WriterRepository yazarDAO;

    public WriterService(WriterRepository yazarDAO) {
        this.yazarDAO = yazarDAO;
    }

    @Override
    public List<Writer> getAll() {
        return yazarDAO.findAll();
    }

    @Override
    public Writer create(Writer request) {
        Writer yazar = new Writer();
        yazar.setName(request.getName());
        yazar.setSurname(request.getSurname());
        return yazarDAO.save(yazar);
    }

}
