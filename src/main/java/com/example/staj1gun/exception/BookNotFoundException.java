package com.example.staj1gun.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "there is no such book")
public class BookNotFoundException extends Exception{
    public BookNotFoundException(){
        super();
    }
    public BookNotFoundException(String message){
        super(message);
    }
    public BookNotFoundException(String message, Throwable cause){
        super(message, cause);
    }
    public BookNotFoundException(Throwable cause){
        super(cause);
    }

    public BookNotFoundException(int id) {
    }
}
