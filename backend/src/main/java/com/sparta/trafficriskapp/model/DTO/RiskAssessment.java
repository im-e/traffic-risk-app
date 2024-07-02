package com.sparta.trafficriskapp.model.DTO;

public class RiskAssessment {
    private double areaRiskValue;
    private String areaRiskText;

    private double customerRiskValue;
    private String customerRiskText;

    private double overallRiskValue;
    private String overallRiskText;

    private int averageMiles;
    private int daysOfRental;

    private int customerAge;
    private int customerYearsExp;

    private GeoLocation geoLocation;
    private Weather currentWeather;
    private byte[] image;

    public RiskAssessment(double overallRiskValue, String overallRiskText, double areaRiskValue, String areaRiskText, double customerRiskValue, String customerRiskText, int averageMiles, int daysOfRental, int customerAge, int customerYearsExp, GeoLocation geoLocation, Weather currentWeather, byte[] image) {
        this.areaRiskValue = areaRiskValue;
        this.areaRiskText = areaRiskText;
        this.averageMiles = averageMiles;
        this.daysOfRental = daysOfRental;
        this.overallRiskValue = overallRiskValue;
        this.overallRiskText = overallRiskText;
        this.customerRiskValue = customerRiskValue;
        this.customerRiskText = customerRiskText;
        this.customerAge = customerAge;
        this.customerYearsExp = customerYearsExp;
        this.geoLocation = geoLocation;
        this.currentWeather = currentWeather;
        this.image = image;
    }

    public double getCustomerRiskValue() {
        return customerRiskValue;
    }

    public void setCustomerRiskValue(double customerRiskValue) {
        this.customerRiskValue = customerRiskValue;
    }

    public String getCustomerRiskText() {
        return customerRiskText;
    }

    public void setCustomerRiskText(String customerRiskText) {
        this.customerRiskText = customerRiskText;
    }

    public double getOverallRiskValue() {
        return overallRiskValue;
    }

    public void setOverallRiskValue(double overallRiskValue) {
        this.overallRiskValue = overallRiskValue;
    }

    public String getOverallRiskText() {
        return overallRiskText;
    }

    public void setOverallRiskText(String overallRiskText) {
        this.overallRiskText = overallRiskText;
    }


    public int getDaysOfRental() {
        return daysOfRental;
    }

    public void setDaysOfRental(int daysOfRental) {
        this.daysOfRental = daysOfRental;
    }

    public int getCustomerAge() {
        return customerAge;
    }

    public void setCustomerAge(int customerAge) {
        this.customerAge = customerAge;
    }

    public int getCustomerYearsExp() {
        return customerYearsExp;
    }

    public void setCustomerYearsExp(int customerYearsExp) {
        this.customerYearsExp = customerYearsExp;
    }

    public Weather getCurrentWeather() {
        return currentWeather;
    }

    public void setCurrentWeather(Weather currentWeather) {
        this.currentWeather = currentWeather;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public double getAreaRiskValue() {
        return areaRiskValue;
    }

    public void setAreaRiskValue(double areaRiskValue) {
        this.areaRiskValue = areaRiskValue;
    }

    public String getAreaRiskText() {
        return areaRiskText;
    }

    public void setAreaRiskText(String areaRiskText) {
        this.areaRiskText = areaRiskText;
    }

    public int getAverageMiles() {
        return averageMiles;
    }

    public void setAverageMiles(int averageMiles) {
        this.averageMiles = averageMiles;
    }

    public GeoLocation getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(GeoLocation geoLocation) {
        this.geoLocation = geoLocation;
    }
}
