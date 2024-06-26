package com.sparta.trafficriskapp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class TrafficService {

    private final WebClient webClient;

    @Value("${tomtom.key}")
    private String apiKey;

    private static final String BASE_URL = "https://api.tomtom.com/traffic/services/5";
    private static final String FIELDS = "{incidents{type,geometry{type,coordinates},properties{id,iconCategory,magnitudeOfDelay,events{description,code,iconCategory},startTime,endTime,from,to,length,delay,roadNumbers,timeValidity,probabilityOfOccurrence,numberOfReports,lastReportTime,tmc{countryCode,tableNumber,tableVersion,direction,points{location,offset}}}}}";

    public TrafficService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(BASE_URL).build();
    }

    public String getIncidentDetails(double lat, double lon, double distance) throws UnsupportedEncodingException {
        String bbox = calculateBoundingBox(lat, lon, distance);

        String encodedFields = URLEncoder.encode(FIELDS, StandardCharsets.UTF_8);

        // Build the URL manually
        String url = String.format("%s/incidentDetails?bbox=%s&fields=%s&key=%s",
                BASE_URL, bbox, encodedFields, apiKey);

        // Log the URL
        System.out.println("Generated URL: " + url);

        // Fetch the data
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/incidentDetails")
                        .queryParam("bbox", bbox)
                        .queryParam("fields", FIELDS)
                        .queryParam("key", apiKey)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
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

