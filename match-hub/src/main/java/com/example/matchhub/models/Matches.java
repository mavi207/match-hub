package com.example.matchhub.models;

import com.example.matchhub.enums.MatchStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data // gives setter, getter, and required arg constructor
// @Data :- also gives required arg constructor, but we have to still initialize the no args and all args
// constructor
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "matches")
public class Matches {

    //Match record is uniquely identified by date, time and venue attributes combined
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "date_of_match",nullable = false)
    LocalDate dateOfMatch;

    @Column(name = "time_of_match",nullable = false)
    LocalTime timeOfMatch;

    @Column(name = "match_status",nullable = false)
    @Enumerated(value = EnumType.STRING)
    MatchStatus matchStatus;

    @ManyToOne
    Player playerOfMatch;
}
