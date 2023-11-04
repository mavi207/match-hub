package com.example.matchhub.controllerlayer;

import com.example.matchhub.dtos.requestdtos.MatchRequest;
import com.example.matchhub.servicelayer.MatchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/match")
public class MatchController {

    private final MatchService matchService;


    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @PostMapping("/addMatching")
    public ResponseEntity addMatching(@RequestBody MatchRequest matchRequest){
        try {
            String message = matchService.addMatching(matchRequest);
            return new ResponseEntity(message, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
