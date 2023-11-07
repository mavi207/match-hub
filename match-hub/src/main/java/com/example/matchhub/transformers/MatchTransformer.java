package com.example.matchhub.transformers;

import com.example.matchhub.dtos.requestdtos.MatchRequest;
import com.example.matchhub.dtos.responsedtos.DetailedMatchResponse;
import com.example.matchhub.dtos.responsedtos.MatchDetailResponse;
import com.example.matchhub.dtos.responsedtos.MatchResponse;
import com.example.matchhub.enums.MatchStatus;
import com.example.matchhub.models.Matches;

import java.time.LocalTime;
import java.util.ArrayList;

import static com.example.matchhub.utilitys.PlayerUtility.PlayerListToPlayerNameList;

public class MatchTransformer {
    public static Matches matchRequestToMatch(MatchRequest matchRequest){
        Matches matches = new Matches();
        matches.setDateOfMatch(matchRequest.getDateOfMatch());
        matches.setTimeOfMatch(LocalTime.parse(matchRequest.getTimeOfMatch()));
        matches.setMatchStatus(MatchStatus.UPCOMING);
        matches.setTeamList(new ArrayList<>(2));
        return matches;
    }

    public static MatchResponse matchToMatchResponse(Matches matches){
        MatchResponse matchResponse = new MatchResponse();
        matchResponse.setId(matches.getId());
        matchResponse.setDateOfMatch(matches.getDateOfMatch());
        matchResponse.setTimeOfMatch(matches.getTimeOfMatch());
        matchResponse.setMatchStatus(matches.getMatchStatus());
        matchResponse.setTeam1(matches.getTeamList().get(0).getTeamName());
        matchResponse.setTeam2(matches.getTeamList().get(1).getTeamName());
        matchResponse.setVenue(matches.getVenue().getVenueName());
        return matchResponse;
    }

    public static MatchDetailResponse matchToMatchDetailResponse(Matches matches){
        MatchDetailResponse matchDetailResponse = new MatchDetailResponse();
        matchDetailResponse.setTeam1(matches.getTeamList().get(0).getTeamName());
        matchDetailResponse.setTeam2(matches.getTeamList().get(1).getTeamName());
        matchDetailResponse.setPlayerOfMatch(matches.getPlayerOfMatch()==null ? "" : matches.getPlayerOfMatch().getPlayerName());
        matchDetailResponse.setVenue(matches.getVenue().getVenueName());
        matchDetailResponse.setTeam1PlayerList(PlayerListToPlayerNameList(matches.getTeamList().get(0).getPlayerList()));
        matchDetailResponse.setTeam2PlayerList(PlayerListToPlayerNameList(matches.getTeamList().get(1).getPlayerList()));

        return matchDetailResponse;
    }

    public static DetailedMatchResponse matchToDetailedMatchResponse(Matches matches){
        return DetailedMatchResponse.builder()
                .team1(matches.getTeamList().get(0).getTeamName())
                .team2(matches.getTeamList().get(1).getTeamName())
                .team1PlayerList(PlayerListToPlayerNameList(matches.getTeamList().get(0).getPlayerList()))
                .team2PlayerList(PlayerListToPlayerNameList(matches.getTeamList().get(1).getPlayerList()))
                .playerOfMatch(matches.getPlayerOfMatch().getPlayerName())
                .winingTeam(matches.getPlayerOfMatch().getTeam().getTeamName())
                .venue(matches.getVenue().getVenueName())
                .build();
    }
}
