package com.sparta.trafficriskapp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MapsService {

    private final RestTemplate restTemplate;

    @Value("${TRAFFICAPI_KEY}")
    private String apiKey;

    private static final String BASE_URL = "https://api.tomtom.com";

    public MapsService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


}
