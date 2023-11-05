package com.example.matchhub.dtos.responsedtos;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MatchResponse {
    int id;

    String dateOfMatch;

    String timeOfMatch;

    String matchStatus;

    String team1;

    String team2;

    String venue;
}
