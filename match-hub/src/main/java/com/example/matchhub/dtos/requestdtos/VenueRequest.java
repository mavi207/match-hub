package com.example.matchhub.dtos.requestdtos;

import com.example.matchhub.enums.Country;
import lombok.Data;

import java.net.URL;

@Data
public class VenueRequest {
    String venueName;
    String city;
    String state;
    Country country;
    int capacity;
    boolean isFloodLight;
    URL locationUrl;
}
