package com.example.matchhub.models;

import com.example.matchhub.enums.Country;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data // gives setter, getter, and required arg constructor
// @Data :- also gives required arg constructor, but we have to still initialize the no args and all args
// constructor
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(nullable = false,name = "player_name")
    String playerName;

    @Column(name = "birth_date",nullable = false)
    LocalDate dateOfBirth;

    @Column(nullable = false, unique = true)
    @Email(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "should be a valid email address")
    String email; //This  attribute identifies the player record uniquely (unique = true)

    @Column(nullable = false)
    double height;

    @Column(nullable = false)
    double weight;

    @Column(nullable = false)
    int numberOfMatchPlayed;// initially it will be zero, assuming the player is starting his career

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    Country country;

    @OneToMany(mappedBy = "playerOfMatch" ,cascade = CascadeType.ALL)
    List<Matches> playerOfTheMatchesList;

    @ManyToOne
    Team team;
}
