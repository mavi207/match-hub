package com.example.matchhub.servicelayer.impl;

import com.example.matchhub.dtos.requestdtos.PlayersRequest;
import com.example.matchhub.exceptions.*;
import com.example.matchhub.models.Player;
import com.example.matchhub.models.Team;
import com.example.matchhub.repositorylayer.PlayerRepository;
import com.example.matchhub.repositorylayer.TeamRepository;
import com.example.matchhub.servicelayer.PlayerService;
import com.example.matchhub.utilitys.TeamUtility;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.matchhub.transformers.PlayerTransformer.playerRequestToPlayer;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    private final TeamRepository teamRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
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


    public String setPlayerTeam(String email, String playerName, String teamName) {
        Optional<Player> optionalPlayer = playerRepository.findByEmail(email);
        if(!optionalPlayer.isPresent()){
            throw new EmailIdIsNotPresent(email+" email is not present!");
        }
        if(optionalPlayer.get().getPlayerName()!=playerName){
            throw new PlayerIsNotPresent(playerName+" player is not present!");
        }

        Optional<Team> optionalTeam = teamRepository.findByTeamName(teamName);
        if(!optionalTeam.isPresent()){
            throw new TeamIsNotPresentException(teamName+" team is not present!");
        }
        if(optionalTeam.get().getPlayerList().size()>=optionalTeam.get().getFixedTeamSize()){
            throw new TeamIsFullException(teamName+" team is full.");
        }

        TeamUtility.setPlayerTeam(optionalTeam.get(),optionalPlayer.get());

        return playerName+" is successfully added in "+teamName+" team.";
    }
}
