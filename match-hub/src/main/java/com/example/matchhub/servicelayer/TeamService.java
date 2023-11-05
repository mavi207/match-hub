package com.example.matchhub.servicelayer;

import com.example.matchhub.dtos.requestdtos.TeamRequest;

public interface TeamService {
    Object addDetailsOfTeam(TeamRequest teamRequest);

    Object setTeamCaptain(String playerName,String playerEmail,String teamName);
}
