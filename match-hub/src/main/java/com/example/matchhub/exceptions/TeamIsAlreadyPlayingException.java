package com.example.matchhub.exceptions;

public class TeamIsAlreadyPlayingException extends RuntimeException{
    public TeamIsAlreadyPlayingException(String message ) {
        super(message);
    }
}
