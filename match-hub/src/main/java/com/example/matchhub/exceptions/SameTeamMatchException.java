package com.example.matchhub.exceptions;

public class SameTeamMatchException extends RuntimeException{
    public SameTeamMatchException(String message){
        super(message);
    }
}
