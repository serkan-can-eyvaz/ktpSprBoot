package com.example.staj1gun.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "there is no such writer")
public class WriterNotFoundException extends Exception {

    //varsayılan contructor
    public WriterNotFoundException() {
        super();
    }
    //özel mesaj ile oluşturduğum constructor
    public WriterNotFoundException(String message) {
        super(message);
    }
    //özel bir mesaj ve cause neden ile oluşturduğum constructor
    public WriterNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    //Sadece cause ile oluşturduğum constructor
    public WriterNotFoundException(Throwable cause) {
        super(cause);
    }
    public WriterNotFoundException(int id) {
    }
}
