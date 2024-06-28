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

    public byte[] getImage(double latitude, double longitude, int distance){

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
        //in miles
        if(distance >= 80) return 10;
        else if(distance >= 60) return 11;
        else if(distance >= 37) return 12;
        else if(distance >= 20) return 13;
        else if(distance >= 8) return 14;
        else return 15;
    }
}
