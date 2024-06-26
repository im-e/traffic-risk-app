package com.sparta.trafficriskapp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class MapsService {

    private final WebClient webClient;

    @Value("${TOMTOMAPI_KEY}")
    private String apiKey;

    private static final String BASE_URL = "https://api.tomtom.com/map/1";

    public MapsService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(BASE_URL).build();
    }

    public byte[] getImage(double latitude, double longitude){

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/staticimage")
                        .queryParam("center", longitude + "," + latitude)
                        .queryParam("key", apiKey)
                        .build())
                .retrieve()
                .bodyToMono(byte[].class)
                .block();
    }
}
