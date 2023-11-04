package com.example.matchhub.exceptions;

public class CapacityIsLessException extends RuntimeException{
    public CapacityIsLessException(String message){
        super(message);
    }
}
