package com.example.matchhub.servicelayer;

import com.example.matchhub.dtos.requestdtos.MatchCompletedRequest;
import com.example.matchhub.dtos.requestdtos.MatchRequest;
import com.example.matchhub.dtos.responsedtos.MatchDetailResponse;
import com.example.matchhub.dtos.responsedtos.MatchResponse;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDate;
import java.util.List;

public interface MatchService {
    String addMatching(MatchRequest matchRequest);

    List<MatchResponse> matchOnDate(LocalDate date);

    MatchDetailResponse getMatchDetails(int id);

    String completeMatch(MatchCompletedRequest matchCompletedRequest);
}
