package com.project.statistic;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.*;

@RestController
@AllArgsConstructor
class SummonerController {

    private final Summoners summoners;
    private final MatchesFacade matchesFacade;
    private final SummonerFacade summonerFacade;

    @GetMapping("/summoner")
    ResponseEntity getSummoner(@RequestParam("name") String name){
        return summoners.getUser(name)
            .map(ResponseEntity::ok)
            .getOrElse(noContent()::build);
    }

    @GetMapping("/summoner/matches")
    ResponseEntity lastTenMatchesByName(@RequestParam("name") String name){
        return matchesFacade.findLastTenMatchesFor(name)
            .map(ResponseEntity::ok)
            .getOrElse(() -> noContent().build());
    }

    @GetMapping("/summoner/winRate") //TODO
    ResponseEntity winRateOfSummoner(@RequestParam("name") String name){
        return summonerFacade.calculateWinRateOfSummoner(name)
            .map(ResponseEntity::ok)
            .getOrElse(() -> noContent().build());
    }

    @GetMapping("/match")
    ResponseEntity findMatch(@RequestParam("id") String name){
        return matchesFacade.findMatch(name)
            .map(ResponseEntity::ok)
            .getOrElse(() -> noContent().build());
    }
}