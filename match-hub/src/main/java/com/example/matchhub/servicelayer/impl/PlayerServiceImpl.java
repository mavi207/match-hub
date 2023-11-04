package com.example.matchhub.servicelayer.impl;

import com.example.matchhub.repositorylayer.PlayerRepository;
import com.example.matchhub.servicelayer.PlayerService;

public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }//Constructor Injection


}
