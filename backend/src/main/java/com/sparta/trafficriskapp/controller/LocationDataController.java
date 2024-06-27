package com.sparta.trafficriskapp.controller;

import com.sparta.trafficriskapp.model.DTO.GeoLocation;
import com.sparta.trafficriskapp.model.DTO.Incidents;
import com.sparta.trafficriskapp.model.DTO.RiskAssessment;
import com.sparta.trafficriskapp.model.DTO.Weather;
import com.sparta.trafficriskapp.service.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LocationDataController {

    private final GeoLocService geoLocService;
    private final WeatherService weatherService;
    private final MapsService mapsService;
    private final TrafficService trafficService;
    private final RiskAssessmentService riskAssessmentService;

    public LocationDataController(GeoLocService geoLocService,
                                  WeatherService weatherService,
                                  MapsService mapsService,
                                  TrafficService trafficService,
                                  RiskAssessmentService riskAssessmentService) {
        this.geoLocService = geoLocService;
        this.weatherService = weatherService;
        this.mapsService = mapsService;
        this.trafficService = trafficService;
        this.riskAssessmentService = riskAssessmentService;
    }

    @GetMapping("/location")
    public GeoLocation getLocation(@RequestParam String zip) {
        return geoLocService.getCurrentLocation(zip);
    }

    @GetMapping("/weather")
    public Weather getDataFromLocation(@RequestParam String zip) {
        GeoLocation location = geoLocService.getCurrentLocation(zip);
        return weatherService.getCurrentWeather(location.getZip());
    }

    @GetMapping("/image")
    public ResponseEntity<byte[]> getImage(@RequestParam String zip, @RequestParam double distance) {
        GeoLocation location = geoLocService.getCurrentLocation(zip);
        byte[] imageBytes = mapsService.getImage(location.getLat(), location.getLon(), distance);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);  // or the correct image MIME type

        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }

    @GetMapping("/incidents")
    public Incidents getIncidents(@RequestParam String zip, @RequestParam double distance) {
        GeoLocation location = geoLocService.getCurrentLocation(zip);
        return trafficService.getIncidents(location.getLat(), location.getLon(), distance);
    }

    @GetMapping("/risk")
    public RiskAssessment getAssessment(@RequestParam String zip, @RequestParam double distance)
    {
        GeoLocation location = geoLocService.getCurrentLocation(zip);
        Incidents incidents = trafficService.getIncidents(location.getLat(), location.getLon(), distance);
        Weather weather = weatherService.getCurrentWeather(location.getZip());
        byte[] image = mapsService.getImage(location.getLat(), location.getLon(), distance);
        return riskAssessmentService.calculateRiskAssessment
                (location, incidents, weather, image, distance);
    }

}
