package com.example.MPR.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String info) {super("User not found");}
}
