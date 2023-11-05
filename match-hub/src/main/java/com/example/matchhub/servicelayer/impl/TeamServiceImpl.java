package com.example.matchhub.servicelayer.impl;

import com.example.matchhub.dtos.requestdtos.TeamRequest;
import com.example.matchhub.exceptions.TeamAlreadyPresentException;
import com.example.matchhub.models.Team;
import com.example.matchhub.repositorylayer.TeamRepository;
import com.example.matchhub.servicelayer.TeamService;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.matchhub.transformers.TeamTransformer.teamRequestToTeam;

@Service
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
