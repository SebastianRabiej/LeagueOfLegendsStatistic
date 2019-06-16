package com.project.statistic;

import io.vavr.control.Option;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Repository;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static com.project.statistic.ApiGateway.API_KEY;

@Repository
@AllArgsConstructor
class Summoners {

    private static final String BY_NAME_API = "https://eun1.api.riotgames.com/lol/summoner/v4/summoners/by-name/";

    private final ApiGateway apiGateway;

    Option<SummonerDto> getUser(String name){
        URI uri = URI.create(BY_NAME_API + name);
        final URI api = UriComponentsBuilder.fromUri(uri)
                .queryParam("api_key", API_KEY).build().toUri();
        return apiGateway.requestEntity(api, SummonerDto.class);
    }
}
