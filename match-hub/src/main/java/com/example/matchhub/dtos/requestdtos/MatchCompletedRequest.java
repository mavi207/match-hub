package com.example.matchhub.dtos.requestdtos;

import lombok.Data;

@Data
public class MatchCompletedRequest{

    int matchId;

    String winTeamName;

    String lostTeamName;

    String playerOfMatch;

    String email;

}
