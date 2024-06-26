package com.sparta.trafficriskapp.service;

import com.sparta.trafficriskapp.model.DTO.GeoLocation;
import com.sparta.trafficriskapp.model.DTO.Incidents;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class GeoLocService {

    private final WebClient webClient;

    @Value("${OPENWEATHERAPI_KEY}")
    private String apiKey;

    private static final String BASE_URL = "https://api.openweathermap.org/geo/1.0";

    public GeoLocService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(BASE_URL).build();
    }

    public GeoLocation getCurrentLocation(String zip, String countryCode) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/zip")
                        .queryParam("zip", zip + ',' + countryCode)
                        .queryParam("appid", apiKey)
                        .build())
                .retrieve()
                .bodyToMono(GeoLocation.class)
                .block();
    }


}
