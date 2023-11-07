package com.example.matchhub.servicelayer.impl;

import com.example.matchhub.dtos.requestdtos.VenueRequest;
import com.example.matchhub.enums.Country;
import com.example.matchhub.exceptions.CapacityIsLessException;
import com.example.matchhub.exceptions.VenueAlreadyPresentException;
import com.example.matchhub.models.Venue;
import com.example.matchhub.repositorylayer.VenueRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VenueServiceImplTest {

    @InjectMocks
    private VenueServiceImpl VenueServiceImpl;

    @Mock
    private VenueRepository venueRepository;

    void setUp() {
        // Initialize the mock objects
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void addDetailsOfVenue_Successfully() throws MalformedURLException {
        VenueRequest venueRequest = new VenueRequest();
        venueRequest.setVenueName("Eden Gardens");
        venueRequest.setCity("Kolkata");
        venueRequest.setState("West Bengal");
        venueRequest.setCountry(Country.INDIA);
        venueRequest.setCapacity(68000);
        venueRequest.setFloodLight(true);
        venueRequest.setLocationUrl(new URL("https://www.google.com/maps?q=Eden+Gardens,Kolkata,India"));

        String res = VenueServiceImpl.addDetailsOfVenue(venueRequest);

        verify(venueRepository, times(1)).save(any(Venue.class));

        assertEquals("Venue Eden Gardens has been added to the database successfully", res);
    }

    @Test
    void addDetailsOfVenue_CapacityIsLessException() throws Exception {
        VenueRequest venueRequest = new VenueRequest();
        venueRequest.setVenueName("Sydney Cricket Ground");
        venueRequest.setCity("Sydney");
        venueRequest.setState("New South Wales");
        venueRequest.setCountry(Country.AUSTRALIA);
        venueRequest.setCapacity(100);
        venueRequest.setFloodLight(true);
        venueRequest.setLocationUrl(new URL("https://www.google.com/maps?q=Sydney+Cricket+Ground,Sydney,Australia"));


//        VenueServiceImpl.addDetailsOfVenue(venueRequest);
        Exception exception = assertThrows(CapacityIsLessException.class, () -> {
            VenueServiceImpl.addDetailsOfVenue(venueRequest);
        });

        String expectedMessage = "Venue's capacity is less than 10000";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void addDetailsOfVenue_VenueAlreadyPresentException() throws Exception {
        VenueRequest venueRequest = new VenueRequest();
        venueRequest.setVenueName("Eden Park");
        venueRequest.setCity("Sydney");
        venueRequest.setState("Auckland");
        venueRequest.setCountry(Country.NEW_ZEALAND);
        venueRequest.setCapacity(50000);
        venueRequest.setFloodLight(true);
        venueRequest.setLocationUrl(new URL("https://www.google.com/maps?q=Eden+Park,Auckland,New+Zealand"));

        // Simulate adding a venue with the same location URL
        when(venueRepository.findByLocationUrl(venueRequest.getLocationUrl())).thenReturn(Optional.of(new Venue()));

        Exception exception = assertThrows(VenueAlreadyPresentException.class, () -> {
            VenueServiceImpl.addDetailsOfVenue(venueRequest);
        });

        String expectedMessage = "Venue Eden Park is already present in the database";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}