package com.example.matchhub.controllerlayer;

import com.example.matchhub.dtos.requestdtos.MatchCompletedRequest;
import com.example.matchhub.dtos.requestdtos.MatchRequest;
import com.example.matchhub.dtos.responsedtos.MatchResponse;
import com.example.matchhub.servicelayer.MatchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/match")
public class MatchController {

    private final MatchService matchService;


    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @PostMapping("/addmatch")
    public ResponseEntity addMatching(@RequestBody MatchRequest matchRequest){
        try {
            String message = matchService.addMatching(matchRequest);
            return new ResponseEntity(message, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/matchondate")
    public ResponseEntity matchOnDate(@RequestParam("date") LocalDate date){
        List<MatchResponse> matchResponseList = matchService.matchOnDate(date);
        return new ResponseEntity(matchResponseList,HttpStatus.ACCEPTED);
    }

    @GetMapping("/getmatchdetail")
    public ResponseEntity getMatchDetails(@RequestParam("matchid") int id){
        try{
            return new ResponseEntity(matchService.getMatchDetails(id),HttpStatus.ACCEPTED);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/completematch")
    public ResponseEntity completeMatch(@RequestBody MatchCompletedRequest matchCompletedRequest){
        try{
            return new ResponseEntity(matchService.completeMatch(matchCompletedRequest),HttpStatus.ACCEPTED);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
