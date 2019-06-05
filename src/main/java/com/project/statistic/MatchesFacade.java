package com.project.statistic;

import io.vavr.control.Option;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Component;

import java.util.stream.DoubleStream;

import static io.vavr.control.Option.none;

@Component
@AllArgsConstructor
class MatchesFacade {

    private final Summoners summoners;
    private final Matches matches;

    Option<MatchListDto> findLastTenMatchesFor(String name) {
        final Option<SummonerDto> user = summoners.getUser(name);
        return user.flatMap(usr -> matches.getMatches(usr.getAccountId())).orElse(none());
    }

    public Option<MatchDto> findMatch(String id) {
        return matches.getMatch(id);
    }
}
