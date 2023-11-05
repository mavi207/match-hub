package com.example.matchhub.servicelayer.impl;

import com.example.matchhub.dtos.requestdtos.TeamRequest;
import com.example.matchhub.exceptions.PlayerIsNotPresent;
import com.example.matchhub.exceptions.TeamAlreadyPresentException;
import com.example.matchhub.exceptions.TeamIsNotPresentException;
import com.example.matchhub.models.Player;
import com.example.matchhub.models.Team;
import com.example.matchhub.repositorylayer.PlayerRepository;
import com.example.matchhub.repositorylayer.TeamRepository;
import com.example.matchhub.servicelayer.TeamService;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.matchhub.transformers.TeamTransformer.teamRequestToTeam;

@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;

    private final PlayerRepository playerRepository;

    public TeamServiceImpl(TeamRepository teamRepository, PlayerRepository playerRepository) {
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
    }

    public Object addDetailsOfTeam(TeamRequest teamRequest) {
        Team team= teamRequestToTeam(teamRequest);
        Optional<Team>teamOptional=teamRepository.findByTeamName(team.getTeamName());
        if(!teamOptional.isPresent()){
            Team savedTeam=teamRepository.save(team);
            return "Team "+savedTeam.getTeamName()+" has been added to the database successfully";
        }else{
            throw new TeamAlreadyPresentException("Team "+team.getTeamName()+" is already present in the database");
        }
    }

    public String setTeamCaptain(String playerName,String playerEmail,String teamName) {
        Optional<Player> optionalPlayer = playerRepository.findByEmail(playerEmail);
        if (!optionalPlayer.isPresent() || !optionalPlayer.get().getPlayerName().equals(playerName)) {
            throw new PlayerIsNotPresent(playerName + " player is not present!");
        }

        Optional<Team> optionalTeam = teamRepository.findByTeamName(teamName);
        if (!optionalTeam.isPresent()) {
            throw new TeamIsNotPresentException(teamName + " team is not present!");
        }

        Team team = optionalTeam.get();
        Player player = optionalPlayer.get();

        // Set the player as the captain for the team
        team.setCaptain(player);

        // Save the updated team to persist the changes
        teamRepository.save(team);

        return playerName + " is set as the captain of " + teamName + " team.";
    }
}
