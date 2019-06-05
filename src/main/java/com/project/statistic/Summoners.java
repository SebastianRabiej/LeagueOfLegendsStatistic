package com.project.statistic;

import io.vavr.control.Option;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Repository
class Summoners {

    private static final String BY_NAME_API = "https://eun1.api.riotgames.com/lol/summoner/v4/summoners/by-name/";
    RestTemplate restTemplate = new RestTemplate();

    Option<SummonerDto> getUser(String name){
        URI uri = URI.create(BY_NAME_API + name);
        final URI api = UriComponentsBuilder.fromUri(uri)
                .queryParam("api_key", "").build().toUri();
        final ResponseEntity<SummonerDto> response = restTemplate.getForEntity(api, SummonerDto.class);
        return Option.of(response.getBody());
    }
}
