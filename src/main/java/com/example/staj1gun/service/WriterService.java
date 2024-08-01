package com.example.staj1gun.service;

import com.example.staj1gun.dao.WriterRepository;
import com.example.staj1gun.dto.mapper.WriterMapper;
import com.example.staj1gun.dto.request.CreateWriterRequest;
import com.example.staj1gun.dto.response.BookResponse;
import com.example.staj1gun.dto.response.WriterResponse;
import com.example.staj1gun.dto.response.getAllWriterResponse;
import com.example.staj1gun.entity.Book;
import com.example.staj1gun.entity.Writer;
import jakarta.persistence.EntityNotFoundException;
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
        return WriterMapper.toGetAllWriterResponseList(writers); // Mapper sınıfını kullanarak dönüştürme
    }

    @Override
    public Writer create(CreateWriterRequest createWriterRequest) {
        Writer writer = new Writer();
        writer.setName(createWriterRequest.getName());
        writer.setSurname(createWriterRequest.getSurname());

        // BookRequest listesini Book title listesine dönüştür
        List<String> bookTitles = new ArrayList<>();
        for (CreateWriterRequest.BookRequest bookRequest : createWriterRequest.getBooks()) {
            bookTitles.add(bookRequest.getTitle());
        }

        // Dönüştürülmüş bookTitles listesini writer'a ekle
        writer.addBooks(bookTitles);
        return writerRepository.save(writer);
    }


    @Override
    public List<WriterResponse> getById(int id) {
        // Proxy nesne döner.
        Writer writer = writerRepository.getReferenceById(id);//tek öğe döneceği zaman kullanılır.

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

        return List.of(responseItem); // Single item listesi döndürdük.
    }

    @Override
    public void deleteById(int id) {
        //findbyId referansı getiren yöntemlere bak
        Writer writer =writerRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("author not found"+id) );
        writerRepository.delete(writer);

    }
}
