package com.example.matchhub.exceptions;

public class TeamIsNotPresentException extends RuntimeException{
    public TeamIsNotPresentException(String message){
        super(message);
    }
}

