package com.sparta.trafficriskapp.model.DTO;

public class RiskAssessment {
    private double riskValue;
    private String riskText;
    private int averageMiles;
    private int daysOfRental;

    private int customerAge;
    private int customerYearsExp;

    private GeoLocation geoLocation;
    private Weather currentWeather;
    private byte[] image;

    public RiskAssessment(double riskValue, String riskText, int averageMiles, int daysOfRental, int customerAge, int customerYearsExp, GeoLocation geoLocation, Weather currentWeather, byte[] image) {
        this.riskValue = riskValue;
        this.riskText = riskText;
        this.averageMiles = averageMiles;
        this.daysOfRental = daysOfRental;

        this.customerAge = customerAge;
        this.customerYearsExp = customerYearsExp;

        this.geoLocation = geoLocation;
        this.currentWeather = currentWeather;
        this.image = image;

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

    public double getRiskValue() {
        return riskValue;
    }

    public void setRiskValue(double riskValue) {
        this.riskValue = riskValue;
    }

    public String getRiskText() {
        return riskText;
    }

    public void setRiskText(String riskText) {
        this.riskText = riskText;
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
