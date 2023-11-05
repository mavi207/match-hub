package com.example.matchhub.utilitys;

import com.example.matchhub.models.Matches;
import com.example.matchhub.models.Player;
import com.example.matchhub.models.Team;
import com.example.matchhub.models.Venue;

public class MatchUtility {
    public static Matches addTeamAndVenueToMatch(Matches matches, Team team1 , Team team2, Venue venue){
        matches.getTeamList().add(team1);
        matches.getTeamList().add(team2);
        matches.setVenue(venue);
        return matches;
    }

//    public static Matches setPlayerOfMatch(Matches matches,Player player){
//       matches.setPlayerOfMatch(player);
//       player.
//    }
}
