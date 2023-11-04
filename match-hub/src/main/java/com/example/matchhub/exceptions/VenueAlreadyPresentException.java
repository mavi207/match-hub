package com.example.matchhub.exceptions;

public class VenueAlreadyPresentException extends RuntimeException{
    public VenueAlreadyPresentException(String message){
        super(message);
    }
}
