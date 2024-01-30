package com.example.MPR.exception;

public class PetAlreadyExistsException extends RuntimeException {
    public PetAlreadyExistsException(String message) {
        super(message);
    }
}

