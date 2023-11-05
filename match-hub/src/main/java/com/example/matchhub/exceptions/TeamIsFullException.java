package com.example.matchhub.exceptions;

public class TeamIsFullException extends RuntimeException{
    public TeamIsFullException(String message){
        super(message);
    }
}
