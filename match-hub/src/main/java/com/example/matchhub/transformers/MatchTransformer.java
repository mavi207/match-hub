package com.example.matchhub.transformers;

import com.example.matchhub.dtos.requestdtos.MatchRequest;
import com.example.matchhub.enums.MatchStatus;
import com.example.matchhub.models.Matches;

import java.time.LocalTime;

public class MatchTransformer {
    public static Matches matchRequestToMatch(MatchRequest matchRequest){
        Matches matches = new Matches();
        matches.setDateOfMatch(matchRequest.getDateOfMatch());
        matches.setTimeOfMatch(LocalTime.parse(matchRequest.getTimeOfMatch()));
        matches.setMatchStatus(MatchStatus.UPCOMING);

        return matches;
    }
}
