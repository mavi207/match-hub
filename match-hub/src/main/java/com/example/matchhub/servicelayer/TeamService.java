package com.example.matchhub.servicelayer;

import com.example.matchhub.dtos.requestdtos.TeamRequest;
import com.example.matchhub.dtos.responsedtos.TeamResponse;
import org.springframework.http.HttpStatusCode;

public interface TeamService {
    String addDetailsOfTeam(TeamRequest teamRequest);

    String setTeamCaptain(String playerName,String playerEmail,String teamName);

    TeamResponse getNumberOfWinAndLost(String teamName);
}
