package com.sparta.trafficriskapp.model.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "crash")
public class Crash {
    @Id
    @Lob
    @Column(name = "ID")
    private String id;

    @Column(name = "Severity")
    private Integer severity;

    @Column(name = "Start_Time")
    private Instant startTime;

    @Column(name = "Start_Lat")
    private Double startLat;

    @Column(name = "Start_Lng")
    private Double startLng;

    @Lob
    @Column(name = "Street")
    private String street;

    @Lob
    @Column(name = "City")
    private String city;

    @Lob
    @Column(name = "County")
    private String county;

    @Column(name = "Zipcode")
    private Integer zipcode;

    @Column(name = "`Humidity(%)`")
    private Double humidity;

    @Lob
    @Column(name = "Wind_Direction")
    private String windDirection;

    @Lob
    @Column(name = "Weather_Condition")
    private String weatherCondition;

    @Lob
    @Column(name = "Sunrise_Sunset")
    private String sunriseSunset;

    @Lob
    @Column(name = "Civil_Twilight")
    private String civilTwilight;

    @Lob
    @Column(name = "Nautical_Twilight")
    private String nauticalTwilight;

    @Lob
    @Column(name = "Astronomical_Twilight")
    private String astronomicalTwilight;

    @Column(name = "`Temperature(C)`")
    private Double temperatureC;

    @Column(name = "`Pressure(cm)`")
    private Double pressureCm;

    @Column(name = "`Visibility(km)`")
    private Double visibilityKm;

    @Column(name = "`Wind_Speed(kph)`")
    private Double windSpeedKph;

    @Column(name = "Year")
    private Double year;

    @Column(name = "Month")
    private Double month;

    @Column(name = "Day")
    private Double day;

    @Column(name = "Hour")
    private Double hour;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSeverity() {
        return severity;
    }

    public void setSeverity(Integer severity) {
        this.severity = severity;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public Double getStartLat() {
        return startLat;
    }

    public void setStartLat(Double startLat) {
        this.startLat = startLat;
    }

    public Double getStartLng() {
        return startLng;
    }

    public void setStartLng(Double startLng) {
        this.startLng = startLng;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public Integer getZipcode() {
        return zipcode;
    }

    public void setZipcode(Integer zipcode) {
        this.zipcode = zipcode;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public String getWeatherCondition() {
        return weatherCondition;
    }

    public void setWeatherCondition(String weatherCondition) {
        this.weatherCondition = weatherCondition;
    }

    public String getSunriseSunset() {
        return sunriseSunset;
    }

    public void setSunriseSunset(String sunriseSunset) {
        this.sunriseSunset = sunriseSunset;
    }

    public String getCivilTwilight() {
        return civilTwilight;
    }

    public void setCivilTwilight(String civilTwilight) {
        this.civilTwilight = civilTwilight;
    }

    public String getNauticalTwilight() {
        return nauticalTwilight;
    }

    public void setNauticalTwilight(String nauticalTwilight) {
        this.nauticalTwilight = nauticalTwilight;
    }

    public String getAstronomicalTwilight() {
        return astronomicalTwilight;
    }

    public void setAstronomicalTwilight(String astronomicalTwilight) {
        this.astronomicalTwilight = astronomicalTwilight;
    }

    public Double getTemperatureC() {
        return temperatureC;
    }

    public void setTemperatureC(Double temperatureC) {
        this.temperatureC = temperatureC;
    }

    public Double getPressureCm() {
        return pressureCm;
    }

    public void setPressureCm(Double pressureCm) {
        this.pressureCm = pressureCm;
    }

    public Double getVisibilityKm() {
        return visibilityKm;
    }

    public void setVisibilityKm(Double visibilityKm) {
        this.visibilityKm = visibilityKm;
    }

    public Double getWindSpeedKph() {
        return windSpeedKph;
    }

    public void setWindSpeedKph(Double windSpeedKph) {
        this.windSpeedKph = windSpeedKph;
    }

    public Double getYear() {
        return year;
    }

    public void setYear(Double year) {
        this.year = year;
    }

    public Double getMonth() {
        return month;
    }

    public void setMonth(Double month) {
        this.month = month;
    }

    public Double getDay() {
        return day;
    }

    public void setDay(Double day) {
        this.day = day;
    }

    public Double getHour() {
        return hour;
    }

    public void setHour(Double hour) {
        this.hour = hour;
    }

}