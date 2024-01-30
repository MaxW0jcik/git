package com.example.MPR.exception;

public class HouseAlreadyExistsException extends RuntimeException {
    public HouseAlreadyExistsException(String message) {
        super(message);
    }
}
