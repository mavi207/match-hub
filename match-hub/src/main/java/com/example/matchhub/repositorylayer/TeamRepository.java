package com.example.matchhub.repositorylayer;

import com.example.matchhub.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team,Integer> {
    Optional<Team> findByTeamName(String name);
}
