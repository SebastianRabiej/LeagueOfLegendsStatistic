package com.project.statistic;

import io.vavr.control.Option;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
class Matches {

    private static final String BY_ACCOUNT_LIST_API = "https://eun1.api.riotgames.com/lol/match/v4/matchlists/by-account/";
    private static final String BY_ID_API = "https://eun1.api.riotgames.com/lol/match/v4/matches/";
    private final RestTemplate restTemplate = new RestTemplate();

    Option<MatchListDto> getMatches(String encryptedAccountId){
        URI uri = URI.create(BY_ACCOUNT_LIST_API + encryptedAccountId);
        final URI api = UriComponentsBuilder.fromUri(uri)
            .queryParam("api_key", "")
            .queryParam("endIndex", "10 ")
            .build()
            .toUri();
        final ResponseEntity<MatchListDto> response = restTemplate.getForEntity(api, MatchListDto.class);
        return Option.of(response.getBody());
    }

    Option<MatchDto> getMatch(String matchId){
        URI uri = URI.create(BY_ID_API + matchId);
        final URI api = UriComponentsBuilder.fromUri(uri)
            .queryParam("api_key", "")
            .build()
            .toUri();
        final ResponseEntity<MatchDto> response = restTemplate.getForEntity(api, MatchDto.class);
        return Option.of(response.getBody());
    }
}
