package com.example.matchhub.transformers;

import com.example.matchhub.dtos.requestdtos.TeamRequest;
import com.example.matchhub.models.Team;

public class TeamTransformer {
    public static Team teamRequestToTeam(TeamRequest teamRequest){
        return Team.builder()
                .teamName(teamRequest.getTeamName())
                .fixedTeamSize(5)
                .numberOfMatchesWon(0)
                .numberOfMatchesLost(0)
                .numberOfMatchesPlayed(0)
                .build();
    }
}
