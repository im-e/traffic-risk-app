package com.sparta.trafficriskapp.model.DTO;

public class RiskAssessment {
    private double Level;
    private String Text;
    private double distance;
    private GeoLocation geoLocation;
    private byte[] image;

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public double getLevel() {
        return Level;
    }

    public void setLevel(double level) {
        this.Level = level;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        this.Text = text;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public GeoLocation getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(GeoLocation geoLocation) {
        this.geoLocation = geoLocation;
    }

    public static RiskAssessment getRiskAssessment(GeoLocation geoLocation, byte[] image,  double distance, double riskLevel) {
        String riskText = switch ((int) (riskLevel * 4)) {
            case 0 -> "Below Average Risk";
            case 1 -> "Average Risk";
            case 2 -> "Above Average Risk";
            case 3 -> "High Risk";
            default -> "Dangerous Risk";
        };

        RiskAssessment riskAssessment = new RiskAssessment();
        riskAssessment.setGeoLocation(geoLocation);
        riskAssessment.setDistance(distance);
        riskAssessment.setLevel(riskLevel);
        riskAssessment.setImage(image);


        riskAssessment.setText(riskText);
        return riskAssessment;
    }
}
