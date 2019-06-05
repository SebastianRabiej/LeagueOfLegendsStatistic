package com.project.statistic;

import io.vavr.control.Option;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Component;

import static io.vavr.control.Option.none;

@Component
@AllArgsConstructor
class SummonerFacade {
    private final MatchesFacade matchesFacade;

    public Option<WinRate> calculateWinRateOfSummoner(String name) {
        final Option<MatchListDto> lastTenSummonerMatches = null;
        matchesFacade
            .findLastTenMatchesFor(name)
            .map(el -> el.getMatches());


        return lastTenSummonerMatches.map(this::calculateWinRate).orElse(none());
    }

    private WinRate calculateWinRate(MatchListDto matchListDto) {
        matchListDto.getMatches();
        return null;
    }
}
