package com.example.matchhub.dtos.requestdtos;

import com.example.matchhub.models.Matches;
import com.example.matchhub.models.Player;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
public class TeamRequest {
    String teamName;
}
