package com.example.matchhub.enums;

public enum Country {
    ARGENTINA ("Argentina"),
    AUSTRALIA ("Australia"),
    BRAZIL ("Brazil"),
    CANADA ("Canada"),
    CHINA ("China"),
    FRANCE ("France"),
    GERMANY ("Germany"),
    INDIA ("India"),
    ITALY ("Italy"),
    JAPAN ("Japan"),
    MEXICO ("Mexico"),
    NETHERLANDS ("Netherlands"),
    NEW_ZEALAND ("New Zealand"),
    RUSSIA ("Russia"),
    SOUTH_AFRICA ("South Africa"),
    SOUTH_KOREA ("South Korea"),
    SPAIN ("Spain"),
    SWEDEN ("Sweden"),
    UNITED_KINGDOM ("United Kingdom"),
    UNITED_STATES ("United States");

    private final String displayName;
    Country(String displayName){
        this.displayName=displayName;
    }
    public String getDisplayName(){
        return this.displayName;
    }
}
