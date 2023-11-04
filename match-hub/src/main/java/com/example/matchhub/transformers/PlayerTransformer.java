package com.example.matchhub.transformers;

import com.example.matchhub.dtos.requestdtos.PlayersRequest;
import com.example.matchhub.models.Player;

public class PlayerTransformer {
    public static Player playerRequestToPlayer(PlayersRequest playersRequest){
        return Player.builder()
                .playerName(playersRequest.getPlayerName())
                .dateOfBirth(playersRequest.getDateOfBirth())
                .email(playersRequest.getEmail())
                .height(playersRequest.getHeight())
                .weight(playersRequest.getWeight())
                .numberOfMatchPlayed(0)
                .country(playersRequest.getCountry())
                .build();
    }
}
