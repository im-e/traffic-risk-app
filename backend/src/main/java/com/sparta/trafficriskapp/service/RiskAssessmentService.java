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
                                                  int distance, int days, int age, int yearsExp) {

        //area risk level
        double areaRiskLevel;
        //incidents
        double incidentRiskLevel = getIncidentRisk(incidents, distance);
        //distance+days usage risk level
        //double usageRiskLevel = getUsageRisk(days, distance);
        //weather risk
        //double weatherRiskLevel = getWeatherRisk(currentWeather);
        areaRiskLevel = incidentRiskLevel;
        String areaRiskText = getRiskText(incidentRiskLevel);

        //driver risk
        double ageRiskLevel = getAgeRiskLevel(age); //age
        double expRiskLevel = getExpRiskLevel(yearsExp); //experience
        double driverRiskLevel = (ageRiskLevel + expRiskLevel) / 2;
        String driverRiskText = getRiskText(driverRiskLevel);

        //overall risk
        double overallRiskLevel = 1;
        String overallRiskText = "Gold Premium";


        return new RiskAssessment(
                overallRiskLevel,overallRiskText,
                areaRiskLevel, areaRiskText,
                driverRiskLevel, driverRiskText,
                distance, age, yearsExp, days, geoLocation, currentWeather, image);
    }

    private static String getRiskText(double riskValue)
    {
        //assuming riskValue 0 -> 1
        return switch ((int) (riskValue * 4)) {
            case 0 -> "Very Low Risk";      // <0.25
            case 1 -> "Low Risk";           // <0.5
            case 2 -> "Average Risk";       // <0.75
            case 3 -> "High Risk";          // <1
            default -> "Very High Risk";    // 1
        };
    }

    private static double getIncidentRisk(Incidents incidents, int distance) {
        double areaRiskLevel = 0;
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
            areaRiskLevel =  incidentRisk * distanceMultiplier;
        }
        return areaRiskLevel;
    }


    private static double getExpRiskLevel(int yearsExp)
    {
        //https://road-safety.transport.ec.europa.eu/european-road-safety-observatory/statistics-and-analysis-archive/young-people/risk_en
        //Rate of incidents halves per km once you're 2 years into driving,
        //with diminishing returns further.
        if(yearsExp <= 2) return 1;
        else if(yearsExp <= 4) return 0.5;
        else if(yearsExp <= 6) return 0.3;
        else if(yearsExp <= 10) return 0.1;
        else return 0.05;
    }

    private static double getAgeRiskLevel(int age)
    {
        //https://road-safety.transport.ec.europa.eu/european-road-safety-observatory/statistics-and-analysis-archive/young-people/risk_en
        //18-20 most risky drivers, goes down until 65, where it starts to increase again.
        if(age <= 20) return 1;
        else if (age <= 24) return  0.8;
        else if(age <= 34) return 0.6;
        else if(age <= 44) return 0.4;
        else if(age <= 59) return 0.3;
        else if(age >= 65) return 0.4;
        else return 0.5;
    }

}
