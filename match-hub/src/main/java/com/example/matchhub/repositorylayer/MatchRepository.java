package com.example.matchhub.repositorylayer;

import com.example.matchhub.enums.MatchStatus;
import com.example.matchhub.models.Matches;
import com.example.matchhub.models.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MatchRepository extends JpaRepository<Matches,Integer> {
    Optional<Matches> findByDateOfMatchAndTimeOfMatchAndVenue(LocalDate dateOfMatch, LocalTime timeOfMatch, Venue venue);

    List<Matches> findByDateOfMatchAndTimeOfMatch(LocalDate dateOfMatch, LocalTime timeOfMatch);

    List<Matches> findByDateOfMatch(LocalDate dateOfMatch);

    List<Matches> findByMatchStatus(MatchStatus matchStatus);
}
