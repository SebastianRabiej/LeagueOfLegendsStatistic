package com.project.statistic;

import io.vavr.control.Option;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static com.project.statistic.ApiGateway.API_KEY;

@Component
@AllArgsConstructor
class Matches {

    private static final String BY_ACCOUNT_LIST_API = "https://eun1.api.riotgames.com/lol/match/v4/matchlists/by-account/";
    private static final String BY_ID_API = "https://eun1.api.riotgames.com/lol/match/v4/matches/";

    private final ApiGateway apiGateway;

    Option<MatchListDto> getMatches(String encryptedAccountId){
        URI uri = URI.create(BY_ACCOUNT_LIST_API + encryptedAccountId);
        final URI api = UriComponentsBuilder.fromUri(uri)
            .queryParam("api_key", API_KEY)
            .queryParam("endIndex", "10")
            .build()
            .toUri();
        return apiGateway.requestEntity(api, MatchListDto.class);
    }

    Option<MatchDto> getMatch(String matchId){
        URI uri = URI.create(BY_ID_API + matchId);
        final URI api = UriComponentsBuilder.fromUri(uri)
            .queryParam("api_key", API_KEY)
            .build()
            .toUri();
        return apiGateway.requestEntity(api, MatchDto.class);
    }
}
