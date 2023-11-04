package com.example.matchhub.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data // gives setter, getter, and required arg constructor
// @Data :- also gives required arg constructor, but we have to still initialize the no args and all args
// constructor
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "team_name",nullable = false,unique = true)
    String teamName;//This  attribute identifies the team record uniquely (unique = true)

    @Column(name = "fixed_team_size")
    int fixedTeamSize;

    @OneToOne
    Player captain;

    @Column(name = "number_of_matches_won",nullable = false)
    int numberOfMatchesWon;

    @Column(name = "number_of_matches_lost",nullable = false)
    int numberOfMatchesLost;

    @Column(name = "number_of_matches_played")
    int numberOfMatchesPlayed;

    @OneToMany(mappedBy = "team",cascade = CascadeType.ALL)
    List<Player>playerList;

    @ManyToMany(mappedBy = "teamList",cascade = CascadeType.ALL)
    List<Matches> matchList;
}
