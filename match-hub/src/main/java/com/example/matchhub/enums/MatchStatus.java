package com.example.matchhub.enums;

public enum MatchStatus {
    UPCOMING("Upcoming"),

    COMPLETED("Completed");


    private final String displayName;
    MatchStatus(String displayName){
        this.displayName=displayName;
    }
    public String getDisplayName(){
        return this.displayName;
    }
}
