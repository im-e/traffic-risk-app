package com.sparta.trafficriskapp.service;

import com.sparta.trafficriskapp.model.DTO.GeoLocation;
import com.sparta.trafficriskapp.model.DTO.Incidents;
import com.sparta.trafficriskapp.model.DTO.RiskAssessment;
import com.sparta.trafficriskapp.model.DTO.Weather;
import org.springframework.stereotype.Service;



@Service
public class RiskAssessmentService {


    public RiskAssessment calculateRiskAssessment(GeoLocation geoLocation, Incidents incidents, Weather currentWeather, double distance) {

        double riskLevel = 0;
        double distanceMultiplier;
        double incidentRiskLevel = 0.05;

        if(distance < 5) distanceMultiplier = 1;
        else if(distance < 10) distanceMultiplier = .75;
        else if (distance < 15) distanceMultiplier = .5;
        else if (distance < 25) distanceMultiplier = .25;
        else if (distance < 30) distanceMultiplier = .1;
        else distanceMultiplier = .05;


        if(!incidents.getIncidents().isEmpty()) {
            double incidentRisk = incidentRiskLevel * incidents.getIncidents().size();
            riskLevel =  incidentRisk * distanceMultiplier;
        }

        return RiskAssessment.getRiskAssessment(geoLocation, distance, riskLevel);

    }



}
