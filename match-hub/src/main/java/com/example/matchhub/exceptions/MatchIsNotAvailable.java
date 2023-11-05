package com.example.matchhub.exceptions;

public class MatchIsNotAvailable extends RuntimeException{
    public MatchIsNotAvailable(String message){
        super(message);
    }
}
