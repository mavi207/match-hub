package com.example.matchhub.repositorylayer;

import com.example.matchhub.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
}
