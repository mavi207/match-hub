package com.example.matchhub.exceptions;

public class VenueIsNotPresentException extends RuntimeException{
    public VenueIsNotPresentException(String message){
        super(message);
    }
}
