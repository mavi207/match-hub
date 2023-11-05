package com.example.matchhub.servicelayer.impl;

import com.example.matchhub.dtos.requestdtos.MatchCompletedRequest;
import com.example.matchhub.dtos.requestdtos.MatchRequest;
import com.example.matchhub.dtos.responsedtos.MatchDetailResponse;
import com.example.matchhub.dtos.responsedtos.MatchResponse;
import com.example.matchhub.enums.MatchStatus;
import com.example.matchhub.exceptions.*;
import com.example.matchhub.models.Matches;
import com.example.matchhub.models.Player;
import com.example.matchhub.models.Team;
import com.example.matchhub.models.Venue;
import com.example.matchhub.repositorylayer.MatchRepository;
import com.example.matchhub.repositorylayer.PlayerRepository;
import com.example.matchhub.repositorylayer.TeamRepository;
import com.example.matchhub.repositorylayer.VenueRepository;
import com.example.matchhub.servicelayer.MatchService;
import com.example.matchhub.utilitys.MatchUtility;
import com.example.matchhub.utilitys.TeamUtility;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.matchhub.transformers.MatchTransformer.*;
import static com.example.matchhub.utilitys.MatchUtility.setPlayerOfMatch;

@Service
public class MatchServiceImpl implements MatchService {
    private final MatchRepository matchRepository;

    private final TeamRepository teamRepository;

    private final VenueRepository venueRepository;

    private final PlayerRepository playerRepository;

    public MatchServiceImpl(MatchRepository matchRepository, TeamRepository teamRepository, VenueRepository venueRepository, PlayerRepository playerRepository) {
        this.matchRepository = matchRepository;
        this.teamRepository = teamRepository;
        this.venueRepository = venueRepository;
        this.playerRepository = playerRepository;
    }
    public String addMatching(MatchRequest matchRequest) {
        // converting matchRequest to match
        Matches matches = matchRequestToMatch(matchRequest);
        // checking if this data is available in databases or not
        Optional<Team> optionalTeam1 = teamRepository.findByTeamName(matchRequest.getTeam1());
        Optional<Team> optionalTeam2 = teamRepository.findByTeamName(matchRequest.getTeam2());
        Optional<Venue> optionalVenue = venueRepository.findByVenueName(matchRequest.getVenue());

        // if any of the team is not available
        if (!optionalTeam1.isPresent() || !optionalTeam2.isPresent()) {
            throw new TeamIsNotPresentException(matchRequest.getTeam1() + " or " + matchRequest.getTeam2() + " is not present in databases");
        }

        // if venue is not available
        if (!optionalVenue.isPresent()) {
            throw new VenueIsNotPresentException(matchRequest.getVenue() + " venue is not present.");
        }

        // both team are same soo no matching can happen
        if (optionalTeam1.get() == optionalTeam2.get()) {
            throw new SameTeamMatchException("Match between the same team cannot be scheduled");
        }

        // teams captain is not set soo no one can lead team in match soo can scheduled match
        if (optionalTeam1.get().getCaptain() == null || optionalTeam2.get().getCaptain() == null) {
            throw new CaptainNotSetException("One of the team's captain is not set");
        }


        Optional<Matches> existingMatch = matchRepository.findByDateOfMatchAndTimeOfMatchAndVenue(matches.getDateOfMatch(), matches.getTimeOfMatch(), matches.getVenue());

        // Venue is busy at the given time can scheduled at this time
        if (existingMatch.isPresent()) {
            throw new MatchIsPresentException("Another match is already scheduled at the given date " + matches.getDateOfMatch() + " and time " + matches.getTimeOfMatch() + " at this venue " + matches.getVenue());
        }

        // Check if either of the teams is already playing at the specified date and time
        List<Matches> matchesAtSameDateTime = matchRepository.findByDateOfMatchAndTimeOfMatch(matches.getDateOfMatch(), matches.getTimeOfMatch());

        for (Matches match : matchesAtSameDateTime) {
            if (match.getTeamList().contains(optionalTeam1.get()) || match.getTeamList().contains(optionalTeam2.get())) {
                throw new TeamIsAlreadyPlayingException(matchRequest.getTeam1() + " or " + matchRequest.getTeam2() + " is already playing at this " + matchRequest.getDateOfMatch() + " " + matchRequest.getTimeOfMatch());
            }
        }

        // if team does not have required player in team to play match
        if (optionalTeam1.get().getPlayerList().size() < optionalTeam1.get().getFixedTeamSize() || optionalTeam2.get().getPlayerList().size() < optionalTeam2.get().getFixedTeamSize()) {
            throw new SufficientPlayersNotAvailableException("Players in teams are not sufficient for the match");
        }

        matches = MatchUtility.addTeamAndVenueToMatch(matches, optionalTeam1.get(), optionalTeam2.get(), optionalVenue.get());
        Matches savedMatch = matchRepository.save(matches);
        TeamUtility.addMatchToTeam(savedMatch, optionalTeam1.get(), optionalTeam2.get());
        optionalVenue.get().getMatchList().add(savedMatch);

        return "Match " + matchRequest.getTeam1() + " and " + matchRequest.getTeam2() + " is scheduled";
    }


    public List<MatchResponse> matchOnDate(LocalDate date) {
        List<Matches> matchesList = matchRepository.findByDateOfMatch(date);

        List<MatchResponse> matchResponseList=new ArrayList<>();
        for(Matches matches: matchesList){
            matchResponseList.add(matchToMatchResponse(matches));
        }

        return matchResponseList;
    }

    public MatchDetailResponse getMatchDetails(int id) {
        Optional<Matches> optionalMatches = matchRepository.findById(id);
        if(!optionalMatches.isPresent()){
            throw new MatchIsNotAvailable(id+" match id is invalid");
        }
        MatchDetailResponse matchDetailResponse = matchToMatchDetailResponse(optionalMatches.get());
        return matchDetailResponse;
    }

    public String completeMatch(MatchCompletedRequest matchCompletedRequest) {
        Optional<Matches> matchesOptional = matchRepository.findById(matchCompletedRequest.getMatchId());
        Optional<Player> playerOptional = playerRepository.findByEmail(matchCompletedRequest.getEmail());
        Optional<Team> optionalWinTeam = teamRepository.findByTeamName(matchCompletedRequest.getWinTeamName());
        Optional<Team> optionalLostTeam = teamRepository.findByTeamName(matchCompletedRequest.getLostTeamName());

        if(!matchesOptional.isPresent()){
            throw new MatchIsNotAvailable(matchCompletedRequest.getMatchId()+" id is not available in databases");
        }

        if(!playerOptional.isPresent()){
            throw new  PlayerIsNotPresent(matchCompletedRequest.getEmail()+" player is not available in databases");
        }

        if(!optionalWinTeam.isPresent() || !optionalLostTeam.isPresent()){
            throw new TeamIsNotPresentException(matchCompletedRequest.getWinTeamName()+" or "+matchCompletedRequest.getLostTeamName()+" is not available");
        }

        Matches match = matchesOptional.get();
        Player player = playerOptional.get();
        Team winTeam = optionalWinTeam.get(); // Get the winning team, if available
        Team lostTeam = optionalLostTeam.get(); // Get the losing team, if available

        // Update the match with completion details
        match.setPlayerOfMatch(player);
        match.setMatchStatus(MatchStatus.COMPLETED);

        // Update match statistics for teams
        winTeam.setNumberOfMatchesWon(winTeam.getNumberOfMatchesWon()+1);
        lostTeam.setNumberOfMatchesLost(lostTeam.getNumberOfMatchesLost()+1);

        winTeam.setNumberOfMatchesPlayed(winTeam.getNumberOfMatchesPlayed()+1);
        lostTeam.setNumberOfMatchesPlayed(lostTeam.getNumberOfMatchesPlayed()+1);

        // Update player statistics
        player.setNumberOfMatchPlayed(player.getNumberOfMatchPlayed() + 1);

        // Save the updated entities
        matchRepository.save(match);
        teamRepository.save(winTeam);
        teamRepository.save(lostTeam);
        playerRepository.save(player);

        return "Match " + matchCompletedRequest.getMatchId() + " is completed with player " + player.getEmail();
    }
}
