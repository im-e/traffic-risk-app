package com.sparta.trafficriskapp.service;

import com.sparta.trafficriskapp.model.DTO.Incidents;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.UnsupportedEncodingException;

@Service
public class TrafficService {

    private final WebClient webClient;

    @Value("${tomtom.key}")
    private String apiKey;

    private static final String BASE_URL = "https://api.tomtom.com/traffic/services/5";
    private static final String FIELDS = "{incidents{type,properties{id,iconCategory,magnitudeOfDelay,events{description,code,iconCategory},startTime,endTime,from,to,length,delay,roadNumbers,timeValidity,probabilityOfOccurrence,numberOfReports,lastReportTime}}}";


    public TrafficService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(BASE_URL).build();
    }

    public Incidents getIncidents(double lat, double lon, double distance) {
        String bbox = calculateBoundingBox(lat, lon, distance);

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/incidentDetails")
                        .queryParam("bbox", bbox)
                        .queryParam("fields", FIELDS)
                        .queryParam("key", apiKey)
                        .build())
                .retrieve()
                .bodyToMono(Incidents.class)
                .block();
    }


    public static String calculateBoundingBox(double latitude, double longitude, double distance) {
        double latDiff = distance / 111.0;
        double lonDiff = distance / (111.0 * Math.cos(Math.toRadians(latitude)));

        double minLat = latitude - latDiff;
        double maxLat = latitude + latDiff;
        double minLon = longitude - lonDiff;
        double maxLon = longitude + lonDiff;

        return String.format("%f,%f,%f,%f", minLon, minLat, maxLon, maxLat);
    }



}

