package com.sparta.trafficriskapp.controller;

import com.sparta.trafficriskapp.model.DTO.GeoLocation;
import com.sparta.trafficriskapp.model.DTO.Incidents;
import com.sparta.trafficriskapp.model.DTO.Weather;
import com.sparta.trafficriskapp.service.GeoLocService;
import com.sparta.trafficriskapp.service.MapsService;
import com.sparta.trafficriskapp.service.TrafficService;
import com.sparta.trafficriskapp.service.WeatherService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;


@RestController
public class LocationDataController {

    private final GeoLocService geoLocService;
    private final WeatherService weatherService;
    private final MapsService mapsService;
    private final TrafficService trafficService;

    public LocationDataController(GeoLocService geoLocService,
                                  WeatherService weatherService,
                                  MapsService mapsService,
                                  TrafficService trafficService) {
        this.geoLocService = geoLocService;
        this.weatherService = weatherService;
        this.mapsService = mapsService;
        this.trafficService = trafficService;
    }

    @GetMapping("/location")
    public GeoLocation getLocation(@RequestParam String zip, @RequestParam String countryCode) {
        return geoLocService.getCurrentLocation(zip, countryCode);
    }

    @GetMapping("/weather")
    public Weather getDataFromLocation(@RequestParam String zip, @RequestParam String countryCode) {
        GeoLocation location = geoLocService.getCurrentLocation(zip, countryCode);
        return weatherService.getCurrentWeather(location.getZip());
    }

    @GetMapping("/image")
    public ResponseEntity<byte[]> getImage(@RequestParam String zip, @RequestParam String countryCode) {
        GeoLocation location = geoLocService.getCurrentLocation(zip, countryCode);
        byte[] imageBytes = mapsService.getImage(location.getLat(), location.getLon());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);  // or the correct image MIME type

        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }

    @GetMapping("/incidents")
    public Incidents getIncidents(@RequestParam String zip,
                                  @RequestParam String countryCode,
                                  @RequestParam double distance) throws UnsupportedEncodingException {

        GeoLocation location = geoLocService.getCurrentLocation(zip, countryCode);
        return trafficService.getIncidents(location.getLat(), location.getLon(), distance);
    }

}
