package com.example.matchhub.utilitys;

import com.example.matchhub.models.Player;
import com.example.matchhub.models.Team;

import java.util.ArrayList;
import java.util.List;

public class PlayerUtility {
    public static List<String> PlayerListToPlayerNameList(List<Player> playerList){
        List<String> playerNameList = new ArrayList<>();
        for(Player player : playerList){
            playerNameList.add(player.getPlayerName());
        }
        return playerNameList;
    }
}
