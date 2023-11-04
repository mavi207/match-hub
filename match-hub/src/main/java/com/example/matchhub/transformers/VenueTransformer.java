package com.example.matchhub.transformers;

import com.example.matchhub.dtos.requestdtos.VenueRequest;
import com.example.matchhub.models.Venue;

public class VenueTransformer {
    public static Venue venueRequestToVenue(VenueRequest venueRequest){
        return Venue.builder()
                .venueName(venueRequest.getVenueName())
                .city(venueRequest.getCity())
                .state(venueRequest.getState())
                .country(venueRequest.getCountry())
                .capacity(venueRequest.getCapacity())
                .isFloodLight(venueRequest.isFloodLight())
                .locationUrl(venueRequest.getLocationUrl())
                .build();
    }
}
