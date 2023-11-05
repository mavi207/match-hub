package com.example.matchhub.utilitys;

import com.example.matchhub.models.Matches;
import com.example.matchhub.models.Player;
import com.example.matchhub.models.Team;

public class TeamUtility {
    public static void addMatchToTeam(Matches matches, Team team1 , Team team2){
        team1.getMatchList().add(matches);
        team2.getMatchList().add(matches);
    }

    public static void setPlayerTeam(Team team, Player player){
        team.getPlayerList().add(player);
        player.setTeam(team);
    }
}
