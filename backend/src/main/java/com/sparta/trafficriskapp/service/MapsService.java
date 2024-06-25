package com.sparta.trafficriskapp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class MapsService {

    private final RestTemplate restTemplate;

    @Value("${TOMTOMAPI_KEY}")
    private String apiKey;

    private static final String BASE_URL = "https://api.tomtom.com/map/1/staticimage";

    public MapsService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public byte[] getImage(double latitude, double longitude){
        String url = UriComponentsBuilder
                .fromHttpUrl(BASE_URL)
                .queryParam("center", longitude + "," + latitude)
                .queryParam("key", apiKey)
                .toUriString();
        return restTemplate.getForObject(url, byte[].class);
    }


}
