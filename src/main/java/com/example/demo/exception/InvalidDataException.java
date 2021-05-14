package com.example.demo.exception;

public class InvalidDataException extends RuntimeException {
    public InvalidDataException(String mssg) {
        super(mssg);
    }

    public InvalidDataException(String mssg, Throwable th) {
        super(mssg, th);
    }
}
