package com.sparta.trafficriskapp.service;

import com.sparta.trafficriskapp.model.DTO.GeoLocation;
import com.sparta.trafficriskapp.model.DTO.Incidents;
import com.sparta.trafficriskapp.model.DTO.RiskAssessment;
import com.sparta.trafficriskapp.model.DTO.Weather;
import org.springframework.stereotype.Service;



@Service
public class RiskAssessmentService {

    public RiskAssessment calculateRiskAssessment(GeoLocation geoLocation, Incidents incidents,
                                                  Weather currentWeather, byte[] image,
                                                  double distance) {

        double riskLevel = 0;
        double distanceMultiplier;
        double incidentRiskLevel = 0.01;

        if(distance < 5) distanceMultiplier = 1;
        else if(distance < 10) distanceMultiplier = .9;
        else if (distance < 15) distanceMultiplier = .85;
        else if (distance < 25) distanceMultiplier = .75;
        else if (distance < 30) distanceMultiplier = .70;
        else distanceMultiplier = .60;


        if(!incidents.getIncidents().isEmpty()) {
            double incidentRisk = incidentRiskLevel * incidents.getIncidents().size();
            riskLevel =  incidentRisk * distanceMultiplier;
        }

        return RiskAssessment.getRiskAssessment(geoLocation, image, distance, riskLevel);

    }

    // Define the minimum and maximum average miles driven per year
    private static final double MIN_MILES = 5000.0;
    private static final double MAX_MILES = 30000.0;

    // Method to normalize miles driven to a value between 0.0 and 0.1
    public static double normalizeMiles(double miles) {
        // Check if miles is within the expected range
        if (miles < MIN_MILES) {
            miles = MIN_MILES;
        } else if (miles > MAX_MILES) {
            miles = MAX_MILES;
        }

        // Normalize the miles to a value between 0.0 and 0.1
        return (miles - MIN_MILES) / (MAX_MILES - MIN_MILES) * 0.1;
    }



}
