package com.sparta.trafficriskapp.service;

import com.sparta.trafficriskapp.model.DTO.GeoLocation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class GeoLocService {

    private final RestTemplate restTemplate;

    @Value("${OPENWEATHERAPI_KEY}")
    private String apiKey;

    private static final String BASE_URL = "https://api.openweathermap.org/geo/1.0/zip";

    public GeoLocService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public GeoLocation getCurrentLocation(String zip, String countryCode) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam("zip", zip + ',' + countryCode)
                .queryParam("appid", apiKey)
                .toUriString();

        return restTemplate.getForObject(url, GeoLocation.class);
    }


}
