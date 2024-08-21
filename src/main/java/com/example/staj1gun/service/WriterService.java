package com.example.staj1gun.service;

import com.example.staj1gun.dao.WriterRepository;
import com.example.staj1gun.dto.mapper.WriterMapper;
import com.example.staj1gun.dto.request.CreateWriterRequest;
import com.example.staj1gun.dto.response.BookResponse;
import com.example.staj1gun.dto.response.WriterResponse;
import com.example.staj1gun.dto.response.GetAllWriterResponse;
import com.example.staj1gun.entity.Book;
import com.example.staj1gun.entity.Writer;
import com.example.staj1gun.exception.WriterNotFoundException; // Yeni exception sınıfını ekliyoruz
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WriterService implements IWriterService {

    private final WriterRepository writerRepository;

    @Autowired
    public WriterService(WriterRepository writerRepository) {
        this.writerRepository = writerRepository;
    }

    @Override
    public List<GetAllWriterResponse> getAll() {
        List<Writer> writers = writerRepository.findAll();
        return WriterMapper.toGetAllWriterResponseList(writers);
    }

    @Override
    public Writer create(CreateWriterRequest createWriterRequest) {
        Writer writer = new Writer();
        writer.setName(createWriterRequest.getName());
        writer.setSurname(createWriterRequest.getSurname());

        List<String> bookTitles = new ArrayList<>();
        for (CreateWriterRequest.BookRequest bookRequest : createWriterRequest.getBooks()) {
            bookTitles.add(bookRequest.getTitle());
        }

        writer.addBooks(bookTitles);
        Writer savedWriter = writerRepository.save(writer);

        return savedWriter;
    }

    @Override
    public List<WriterResponse> getById(int id) throws WriterNotFoundException {
        Optional<Writer> optionalWriter = writerRepository.findById(id);
        if (optionalWriter.isEmpty()) {
            throw new WriterNotFoundException("Writer not found with id: " + id);
        }
        Writer writer = optionalWriter.get();

        WriterResponse responseItem = new WriterResponse();
        responseItem.setName(writer.getName());
        responseItem.setSurname(writer.getSurname());

        List<BookResponse> bookResponses = new ArrayList<>();
        for (Book book : writer.getBooks()) {
            BookResponse bookResponse = new BookResponse();
            bookResponse.setTitle(book.getTitle());
            bookResponses.add(bookResponse);
        }
        responseItem.setBookResponses(bookResponses);

        return List.of(responseItem);
    }

    @Override
    public void deleteById(int id) throws WriterNotFoundException {
        Writer writer = writerRepository.findById(id)
                .orElseThrow(() -> new WriterNotFoundException("Writer not found with id: " + id));
        writerRepository.delete(writer);
    }
}
