package com.example.MPR.exception;

public class PetNotFoundException extends RuntimeException {
    public PetNotFoundException(String s) {super("Pet not found");}
}