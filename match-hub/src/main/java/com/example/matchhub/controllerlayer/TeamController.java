package com.example.matchhub.controllerlayer;

import com.example.matchhub.dtos.requestdtos.TeamRequest;
import com.example.matchhub.servicelayer.impl.TeamServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
