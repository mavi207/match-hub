package com.example.matchhub.repositorylayer;

import com.example.matchhub.models.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.net.URL;
import java.util.Optional;

@Repository
public interface VenueRepository extends JpaRepository<Venue,Integer> {
    Optional<Venue> findByLocationUrl(URL locationUrl);
}
