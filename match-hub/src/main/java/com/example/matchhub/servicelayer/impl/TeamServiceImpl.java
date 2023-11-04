package com.example.matchhub.servicelayer.impl;

import com.example.matchhub.dtos.requestdtos.PlayersRequest;
import com.example.matchhub.dtos.requestdtos.TeamRequest;
import com.example.matchhub.exceptions.PlayerAlreadyPresentException;
import com.example.matchhub.exceptions.TeamAlreadyPresentException;
import com.example.matchhub.models.Player;
import com.example.matchhub.models.Team;
import com.example.matchhub.repositorylayer.TeamRepository;
import com.example.matchhub.servicelayer.TeamService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.matchhub.transformers.PlayerTransformer.playerRequestToPlayer;
import static com.example.matchhub.transformers.TeamTransformer.teamRequestToTeam;

public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;

    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
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
}
