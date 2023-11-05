package com.example.matchhub.dtos.responsedtos;

import com.example.matchhub.enums.MatchStatus;
import com.example.matchhub.models.Player;
import com.example.matchhub.models.Team;
import com.example.matchhub.models.Venue;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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
