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
                                                  int distance, int age, int yearsExp) {

        double riskLevel = 0;
        double distanceMultiplier;
        double incidentRiskLevel = 0.01;

        if(distance < 5)        distanceMultiplier = 1;
        else if(distance < 10)  distanceMultiplier = .9;
        else if (distance < 15) distanceMultiplier = .85;
        else if (distance < 25) distanceMultiplier = .75;
        else if (distance < 30) distanceMultiplier = .70;
        else                    distanceMultiplier = .60;


        if(!incidents.getIncidents().isEmpty()) {
            double incidentRisk = incidentRiskLevel * incidents.getIncidents().size();
            riskLevel =  incidentRisk * distanceMultiplier;
        }

        String riskText = switch ((int) (riskLevel * 4)) {
            case 0 -> "Below Average Risk";
            case 1 -> "Average Risk";
            case 2 -> "Above Average Risk";
            case 3 -> "High Risk";
            default -> "Dangerous Risk";
        };

        return new RiskAssessment(riskLevel, riskText, distance, geoLocation, age, yearsExp);
    }

}
