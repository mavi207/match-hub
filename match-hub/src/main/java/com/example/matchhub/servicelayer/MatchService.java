package com.example.matchhub.servicelayer;

import com.example.matchhub.dtos.requestdtos.MatchRequest;

public interface MatchService {
    String addMatching(MatchRequest matchRequest);
}
