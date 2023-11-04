package com.example.matchhub.dtos.requestdtos;

import com.example.matchhub.enums.Country;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PlayersRequest {
    String playerName;
    LocalDate dateOfBirth;
    String email;
    double height;
    double weight;
    Country country;
}
