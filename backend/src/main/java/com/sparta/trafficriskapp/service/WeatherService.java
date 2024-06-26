package com.sparta.trafficriskapp.service;


import com.sparta.trafficriskapp.model.DTO.Weather;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class WeatherService {

    private final WebClient webClient;

    @Value("${weatherapi.key}")
    private String apiKey;

    private static final String BASE_URL = "https://api.weatherapi.com/v1";

    public WeatherService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(BASE_URL).build();
    }

    public Weather getCurrentWeather(String location) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .pathSegment("current.json")
                        .queryParam("key", apiKey)
                        .queryParam("q", location)
                        .build())
                .retrieve()
                .bodyToMono(Weather.class)
                .block();
    }

}