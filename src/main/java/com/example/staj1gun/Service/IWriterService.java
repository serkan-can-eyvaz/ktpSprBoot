package com.example.staj1gun.Service;

import com.example.staj1gun.Entity.Writer;

import java.util.List;

public interface IWriterService {
    List<Writer> getAll();
    Writer create(Writer writer);
}
