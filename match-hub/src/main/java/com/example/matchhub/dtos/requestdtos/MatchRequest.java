package com.example.matchhub.dtos.requestdtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MatchRequest {
    LocalDate dateOfMatch;

    String timeOfMatch;

    String team1;

    String team2;

    String venue;
}
