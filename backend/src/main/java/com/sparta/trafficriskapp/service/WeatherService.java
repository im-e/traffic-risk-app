package com.sparta.trafficriskapp.service;


import com.sparta.trafficriskapp.model.DTO.Weather;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class WeatherService {

    private final RestTemplate restTemplate;

    @Value("${weatherapi.key}")
    private String apiKey;

    private static final String BASE_URL = "https://api.weatherapi.com/v1";

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Weather getCurrentWeather(String location) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .pathSegment("current.json")
                .queryParam("key", apiKey)
                .queryParam("q", location)
                .toUriString();

        return restTemplate.getForObject(url, Weather.class);
    }

}