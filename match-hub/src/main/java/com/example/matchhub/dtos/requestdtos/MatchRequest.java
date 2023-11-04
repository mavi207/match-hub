package com.example.matchhub.dtos.requestdtos;

import com.example.matchhub.enums.MatchStatus;
import com.example.matchhub.models.Player;
import com.example.matchhub.models.Team;
import com.example.matchhub.models.Venue;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class MatchRequest {
    LocalDate dateOfMatch;

    LocalTime timeOfMatch;

    String team1;

    String team2;

    String venue;
}
