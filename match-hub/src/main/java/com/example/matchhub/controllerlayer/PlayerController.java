package com.example.matchhub.controllerlayer;

import com.example.matchhub.dtos.requestdtos.PlayersRequest;
import com.example.matchhub.servicelayer.impl.PlayerServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {
    private final PlayerServiceImpl playerServiceimpl;

    public PlayerController(PlayerServiceImpl playerServiceimpl) {
        this.playerServiceimpl = playerServiceimpl;
    }//Constructor Injection

    //Method 1: addTeam
    @PostMapping("/addDetails")
    public ResponseEntity addDetailsOfPlayer(@RequestBody List<PlayersRequest> playersRequestList){
        try{
            String message = playerServiceimpl.addDetailsOfPlayer(playersRequestList);
            return new ResponseEntity(message, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
