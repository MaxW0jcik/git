package com.example.MPR.exception;

public class HouseNotFoundException extends RuntimeException {
    public HouseNotFoundException(String s) {super("House not found");}
}
