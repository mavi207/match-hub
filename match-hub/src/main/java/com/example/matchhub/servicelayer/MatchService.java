package com.example.matchhub.servicelayer;

import com.example.matchhub.dtos.requestdtos.MatchCompletedRequest;
import com.example.matchhub.dtos.requestdtos.MatchRequest;
import com.example.matchhub.dtos.responsedtos.DetailedMatchResponse;
import com.example.matchhub.dtos.responsedtos.MatchDetailResponse;

import java.util.List;

public interface MatchService {
    String addMatching(MatchRequest matchRequest);

    List<String> matchOnDate(String date);

    MatchDetailResponse getMatchDetails(int id);

    String completeMatch(MatchCompletedRequest matchCompletedRequest);

    List<DetailedMatchResponse> pastMatch();
}
