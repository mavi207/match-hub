package com.example.matchhub.utilitys;

import com.example.matchhub.models.Matches;
import com.example.matchhub.models.Team;

public class TeamUtility {
    public static void addMatchToTeam(Matches matches, Team team1 , Team team2){
        team1.getMatchList().add(matches);
        team2.getMatchList().add(matches);
    }
}
