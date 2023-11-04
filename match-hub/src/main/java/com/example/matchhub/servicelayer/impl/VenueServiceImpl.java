package com.example.matchhub.servicelayer.impl;

import com.example.matchhub.dtos.requestdtos.VenueRequest;
import com.example.matchhub.exceptions.TeamAlreadyPresentException;
import com.example.matchhub.exceptions.VenueAlreadyPresentException;
import com.example.matchhub.models.Team;
import com.example.matchhub.models.Venue;
import com.example.matchhub.repositorylayer.VenueRepository;
import com.example.matchhub.servicelayer.VenueService;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.matchhub.transformers.VenueTransformer.venueRequestToVenue;

@Service
public class VenueServiceImpl implements VenueService {
    private final VenueRepository venueRepository;

    public VenueServiceImpl(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    public String addDetailsOfVenue(VenueRequest venueRequest) {
        Venue venue = venueRequestToVenue(venueRequest);
        Optional<Venue> venueOptional=venueRepository.findByLocationUrl(venue.getLocationUrl());
        if(!venueOptional.isPresent()){
            Venue savedVenue=venueRepository.save(venue);
            return "Venue "+savedVenue.getVenueName()+" has been added to the database successfully";
        }else{
            throw new VenueAlreadyPresentException("Venue "+venue.getVenueName()+" is already present in the database");
        }
    }
}
