package com.example.matchhub.servicelayer.impl;

import com.example.matchhub.dtos.requestdtos.MatchRequest;
import com.example.matchhub.exceptions.*;
import com.example.matchhub.models.Matches;
import com.example.matchhub.models.Team;
import com.example.matchhub.models.Venue;
import com.example.matchhub.repositorylayer.MatchRepository;
import com.example.matchhub.repositorylayer.TeamRepository;
import com.example.matchhub.repositorylayer.VenueRepository;
import com.example.matchhub.servicelayer.MatchService;
import com.example.matchhub.utilitys.MatchUtility;
import com.example.matchhub.utilitys.TeamUtility;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.matchhub.transformers.MatchTransformer.matchRequestToMatch;

@Service
public class MatchServiceImpl implements MatchService {
    private final MatchRepository matchRepository;

    private final TeamRepository teamRepository;

    private final VenueRepository venueRepository;

    public MatchServiceImpl(MatchRepository matchRepository, TeamRepository teamRepository, VenueRepository venueRepository) {
        this.matchRepository = matchRepository;
        this.teamRepository = teamRepository;
        this.venueRepository = venueRepository;
    }

    public String addMatching(MatchRequest matchRequest) {
        Matches matches = matchRequestToMatch(matchRequest);
        Optional<Team> optionalTeam1= teamRepository.findByTeamName(matchRequest.getTeam1());
        Optional<Team> optionalTeam2= teamRepository.findByTeamName(matchRequest.getTeam2());
        Optional<Venue> optionalVenue = venueRepository.findByVenueName(matchRequest.getVenue());

        if(!(optionalTeam1.isPresent()||optionalTeam2.isPresent())){
            throw  new TeamIsNotPresentException(matchRequest.getTeam1()+" or "+matchRequest.getTeam2()+" is not present in databases");
        }

        if(!(optionalVenue.isPresent())){
            throw new VenueIsNotPresentException(matchRequest.getVenue()+" venue is not present.");
        }

        if(optionalTeam1==optionalTeam2) throw new SameTeamMatchException("Match between same team cannot be scheduled");

        if(optionalTeam1.get().getCaptain()==null||optionalTeam2.get().getCaptain()==null){
            throw new CaptainNotSetException("One of team's captain is not set");
        }

        Optional<Matches> optionalMatches = matchRepository.findByDateOfMatchAndTimeOfMatchAndVenue(matches.getDateOfMatch(),matches.getTimeOfMatch(),matches.getVenue());

        if(optionalMatches.isPresent()){
            throw new MatchIsPresentException("Another match is already present at given date "+matches.getDateOfMatch()+" and time "+matches.getTimeOfMatch()+" at this venue "+matches.getVenue());
        }

        List<Matches> optionalMatches1 = matchRepository.findByDateOfMatchAndTimeOfMatch(matches.getDateOfMatch(),matches.getTimeOfMatch());

        for(Matches matches1 : optionalMatches1){
            if(matches1.getTeamList().contains(matchRequest.getTeam1())){
                throw new TeamIsAlreadyPlayingException(matchRequest.getTeam1()+" is already playing at this "+matchRequest.getDateOfMatch()+" "+matchRequest.getTimeOfMatch());
            }
            if(matches1.getTeamList().contains(matchRequest.getTeam2())){
                throw new TeamIsAlreadyPlayingException(matchRequest.getTeam2()+" is already playing at this "+matchRequest.getDateOfMatch()+" "+matchRequest.getTimeOfMatch());
            }
        }
        if(optionalTeam1.get().getPlayerList().size()<optionalTeam1.get().getFixedTeamSize()||optionalTeam2.get().getPlayerList().size()<optionalTeam2.get().getFixedTeamSize()){
            throw new SufficientPlayersNotAvailableException("Player in teams are not sufficient for match");
        }

        matches= MatchUtility.addTeamAndVenueToMatch(matches,optionalTeam1.get(),optionalTeam2.get(),optionalVenue.get());
        Matches matches1 = matchRepository.save(matches);
        TeamUtility.addMatchToTeam(matches1,optionalTeam1.get(),optionalTeam2.get());
        optionalVenue.get().getMatchList().add(matches1);
        return "Match "+matchRequest.getTeam1()+ " and "+matchRequest.getTeam2()+" is scheduled ";
    }
}
