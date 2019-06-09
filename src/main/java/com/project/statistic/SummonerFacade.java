package com.project.statistic;

import io.vavr.collection.List;
import io.vavr.control.Option;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Component;

import static com.project.statistic.WinEnum.Win;
import static io.vavr.control.Option.none;
import static java.util.function.Function.identity;

@Component
@AllArgsConstructor
class SummonerFacade {
    private final MatchesFacade matchesFacade;

    public Option<WinRate> calculateWinRateOfSummoner(String name) {
        return matchesFacade
            .findLastTenMatchesFor(name)
            .map(this::fetchMatch)
            .map(matches -> calculateWinRate(matches, name))
            .orElse(none());
    }

    private List<MatchDto> fetchMatch(MatchListDto matches) {
        return matches.getMatches()
            .map(MatchReferenceDto::getGameId)
            .map(this::findMatch)
            .flatMap(identity());
    }

    private Option<MatchDto> findMatch(Long id) {
        return matchesFacade.findMatch(id.toString());
    }

    private WinRate calculateWinRate(List<MatchDto> matches, String name) {
        final int size = matches
            .map(matchDto -> isWin(matchDto, name))
            .filter(i -> i)
            .size();
        return new WinRate((double) size/  (double) matches.length());
    }

    private Boolean isWin(MatchDto matchDto, String name) {
        final int teamId = findTeamId(matchDto, name);
        return matchDto.getTeams()
            .filter(teamStats -> teamStats.getTeamId() == teamId)
            .head()
            .getWin().equals(Win.toString());
    }

    private int findTeamId(MatchDto matchDto, String name) {
        final int participantId = matchDto.getParticipantIdentities()
            .filter(participant -> participant.getPlayer().getSummonerName().equals(name))
            .head()
            .getParticipantId();

        return matchDto.getParticipants()
            .filter(participant -> participant.getParticipantId() == participantId)
            .head()
            .getTeamId();
    }
}
