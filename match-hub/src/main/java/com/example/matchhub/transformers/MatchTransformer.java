package com.example.matchhub.transformers;

import com.example.matchhub.dtos.requestdtos.MatchRequest;
import com.example.matchhub.dtos.responsedtos.MatchResponse;
import com.example.matchhub.enums.MatchStatus;
import com.example.matchhub.models.Matches;

import java.time.LocalTime;
import java.util.ArrayList;

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
        return MatchResponse.builder()
                .id(matches.getId())
                .dateOfMatch(String.valueOf(matches.getDateOfMatch()))
                .timeOfMatch(String.valueOf(matches.getTimeOfMatch()))
                .matchStatus(String.valueOf(matches.getMatchStatus()))
                .team1(String.valueOf(matches.getTeamList().get(0)))
                .team2(String.valueOf(matches.getTeamList().get(1)))
                .venue(String.valueOf(matches.getVenue()))
                .build();
    }
}
