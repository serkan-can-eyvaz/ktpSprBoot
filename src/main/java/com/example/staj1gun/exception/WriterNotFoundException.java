package com.example.staj1gun.exception;

//checked dışında unchecked tercih ediliyor mu
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
}
