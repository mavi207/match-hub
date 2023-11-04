package com.example.matchhub.servicelayer.impl;

import com.example.matchhub.dtos.requestdtos.PlayersRequest;
import com.example.matchhub.exceptions.PlayerAlreadyPresentException;
import com.example.matchhub.models.Player;
import com.example.matchhub.repositorylayer.PlayerRepository;
import com.example.matchhub.servicelayer.PlayerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.matchhub.transformers.PlayerTransformer.playerRequestToPlayer;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }//Constructor Injection


    public String addDetailsOfPlayer(List<PlayersRequest> playersRequestList) {
        List<String> duplicateEntries = new ArrayList<>();
        for(PlayersRequest playersRequest : playersRequestList){
            Player player = playerRequestToPlayer(playersRequest);
            Optional<Player> optionalPlayer = playerRepository.findByEmail(player.getEmail());
            if(optionalPlayer.isPresent()){
                duplicateEntries.add(player.getPlayerName());
            }
            else{
                playerRepository.save(player);
            }
        }
        if(duplicateEntries.size()!=0){
            throw new PlayerAlreadyPresentException("Player "+duplicateEntries+" is/are already present in database");
        }
        else if(playersRequestList.size()==1){
            return "Player "+playersRequestList.get(0).getPlayerName()+" is added successfully to database";
        }else {
            return "Players are added successfully to database";
        }
    }
}
