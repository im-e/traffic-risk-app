package com.sparta.trafficriskapp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class MapsService {

    private final WebClient webClient;

    @Value("${TOMTOMAPI_KEY}")
    private String apiKey;

    private static final String BASE_URL = "https://api.tomtom.com/map/1";

    public MapsService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(BASE_URL).build();
    }

    public byte[] getImage(double latitude, double longitude, double distance){

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/staticimage")
                        .queryParam("center", longitude + "," + latitude)
                        .queryParam("zoom", calculateZoom(distance))
                        .queryParam("key", apiKey)
                        .build())
                .retrieve()
                .bodyToMono(byte[].class)
                .block();
    }

    public static int calculateZoom(double distance)
    {
        if(distance >= 40) return 10;
        else if(distance > 30) return 11;
        else if(distance > 20) return 12;
        else if(distance > 10) return 13;
        else return 14;
    }
}
