package com.example.matchhub.exceptions;

public class TeamAlreadyPresentException extends RuntimeException{
    public TeamAlreadyPresentException(String message){
        super(message);
    }
}
