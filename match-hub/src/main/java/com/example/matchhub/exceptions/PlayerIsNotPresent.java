package com.example.matchhub.exceptions;

public class PlayerIsNotPresent extends RuntimeException{
    public PlayerIsNotPresent(String message){
        super(message);
    }
}
