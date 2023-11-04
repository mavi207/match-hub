package com.example.matchhub.dtos.requestdtos;

import com.example.matchhub.enums.Country;
import com.example.matchhub.models.Matches;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.net.URL;
import java.util.List;

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
