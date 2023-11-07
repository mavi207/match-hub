package com.example.matchhub.dtos.responsedtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamResponse {
    String teamName;

    int numberOfMatchesWon;

    int numberOfMatchesLost;

    int numberOfMatchesPlayed;
}
