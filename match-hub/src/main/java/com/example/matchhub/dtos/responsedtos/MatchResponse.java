package com.example.matchhub.dtos.responsedtos;

import com.example.matchhub.enums.MatchStatus;
import com.example.matchhub.models.Venue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchResponse {
    int id;

    LocalDate dateOfMatch;

    LocalTime timeOfMatch;

    MatchStatus matchStatus;

    String team1;

    String team2;

    String venue;
}
