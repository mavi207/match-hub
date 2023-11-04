package com.example.matchhub.exceptions;

public class PlayerAlreadyPresentException extends RuntimeException{
    public PlayerAlreadyPresentException(String message){
        super(message);
    }
}
