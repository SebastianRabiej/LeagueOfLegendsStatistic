package com.project.statistic;

import io.vavr.control.Option;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component
@RequiredArgsConstructor
class ApiGateway {

    static final String API_KEY = "";
    private static final int LIMIT_PER_TWO_MINUTES= 100;
    private final RestTemplate restTemplate;
    private int hits;

    synchronized <T> Option<T> requestEntity(URI api, Class<T> type){
        System.out.println(hits);
        hitsLimitCheck();
        final ResponseEntity<T> response = sendRequestToApi(api, type);
        increaseHits();
        return Option.of(response.getBody());
    }

    private void hitsLimitCheck(){
        if(hits == LIMIT_PER_TWO_MINUTES){
            waitTwoMinutes();
            resetHits();
        }
    }

    private void waitTwoMinutes() {
        try {
            Thread.sleep(120000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void resetHits() {
        hits=0;
    }

    private <T> ResponseEntity<T> sendRequestToApi(URI api, Class<T> type) {
        return restTemplate.getForEntity(api, type);
    }

    private void increaseHits() {
        hits++;
    }
}
