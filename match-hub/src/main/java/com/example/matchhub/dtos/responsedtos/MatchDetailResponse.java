package com.example.matchhub.dtos.responsedtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchDetailResponse {

    String team1;

    List<String> team1PlayerList = new ArrayList<>();

    String team2;

    List<String> team2PlayerList = new ArrayList<>();

    String playerOfMatch;

    String venue;
}
