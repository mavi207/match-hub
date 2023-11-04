package com.example.matchhub.exceptions;

public class SufficientPlayersNotAvailableException extends RuntimeException{
    public SufficientPlayersNotAvailableException(String message){
        super(message);
    }
}
