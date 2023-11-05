package com.example.matchhub.controllerlayer;

import com.example.matchhub.dtos.requestdtos.TeamRequest;
import com.example.matchhub.servicelayer.impl.TeamServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/team")
public class TeamController {
    private final TeamServiceImpl teamServiceimpl;

    public TeamController(TeamServiceImpl teamServiceimpl) {
        this.teamServiceimpl = teamServiceimpl;
    }//Constructor Injection

    @PostMapping("/addDetails")
    public ResponseEntity addDetailsOfTeam(@RequestBody TeamRequest teamRequest){
        try{
            return new ResponseEntity(teamServiceimpl.addDetailsOfTeam(teamRequest), HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/setTeamCaptain")
    public ResponseEntity setTeamCaptain(@RequestParam("playerName") String playerName,@RequestParam("playerEmail") String playerEmail,@RequestParam("teamName") String teamName){
        try{
            return new ResponseEntity(teamServiceimpl.setTeamCaptain(playerName,playerEmail,teamName), HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
