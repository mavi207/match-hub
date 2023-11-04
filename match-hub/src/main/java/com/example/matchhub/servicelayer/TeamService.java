package com.example.matchhub.servicelayer;

import com.example.matchhub.dtos.requestdtos.TeamRequest;

import java.util.List;

public interface TeamService {
    Object addDetailsOfTeam(TeamRequest teamRequest);
}
