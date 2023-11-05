package com.example.matchhub.servicelayer;

import com.example.matchhub.dtos.requestdtos.PlayersRequest;

import java.util.List;

public interface PlayerService {
    String addDetailsOfPlayer(List<PlayersRequest> playersRequestList);

    String setPlayerTeam(String email, String playerName, String teamName);
}
