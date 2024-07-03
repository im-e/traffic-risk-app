package com.sparta.trafficriskapp.service;

import com.sparta.trafficriskapp.model.DTO.GeoLocation;
import com.sparta.trafficriskapp.model.DTO.Incidents;
import com.sparta.trafficriskapp.model.DTO.RiskAssessment;
import com.sparta.trafficriskapp.model.DTO.Weather;
import com.sparta.trafficriskapp.model.exception.AgeInvalidException;
import com.sparta.trafficriskapp.model.exception.DrivingExperienceHigherThanAgeException;
import com.sparta.trafficriskapp.model.exception.DrivingExperienceInvalidException;
import com.sparta.trafficriskapp.model.repository.CrashRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class RiskAssessmentService {

    private final AccidentAssessor accidentAssessor;
    private final CrashRepository crashRepository;

    public RiskAssessmentService(AccidentAssessor accidentAssessor,
                                 CrashRepository crashRepository) {
        this.accidentAssessor = accidentAssessor;
        this.crashRepository = crashRepository;
    }

    public RiskAssessment calculateRiskAssessment(GeoLocation geoLocation, Incidents incidents,
                                                  Weather currentWeather, byte[] image,
                                                  int distance, int days, int age, int yearsExp) throws AgeInvalidException, DrivingExperienceHigherThanAgeException, DrivingExperienceInvalidException {

        int legalDriverAge = 16;
        int legalRentingAge = 18;

        if (age < legalRentingAge) {
            throw new AgeInvalidException();
        }

        if (yearsExp > age)
        {
            throw new DrivingExperienceHigherThanAgeException();
        }

        if ((age - yearsExp) < legalDriverAge)
        {
            throw new DrivingExperienceInvalidException();
        }

        //incidents
        double incidentRiskLevel = getIncidentRisk(incidents, distance);
        System.out.println("incident: " + incidentRiskLevel);

        //distance+days usage risk level
        double usageRiskLevel = getUsageRisk(days, distance);
        System.out.println("usage: " + usageRiskLevel);
      
        //city to state risk
        double cityRiskLevel = getCityRisk(currentWeather);
        System.out.println("city: " + cityRiskLevel);

        //city temp risk
        double tempRiskLevel = getTempRisk(currentWeather);
        System.out.println("temp: " + tempRiskLevel);

        //area risk level
        double areaRiskLevel = incidentRiskLevel + usageRiskLevel + cityRiskLevel;
        areaRiskLevel = areaRiskLevel / 3;
        System.out.println("area: " + areaRiskLevel);

        String areaRiskText = getRiskText(areaRiskLevel);

        //customer risk
        double ageRiskLevel = getAgeRiskLevel(age); //age
        double expRiskLevel = getExpRiskLevel(yearsExp); //experience
        double customerRiskLevel = (ageRiskLevel + expRiskLevel) / 2;
        String customerRiskText = getRiskText(customerRiskLevel);

        //overall risk
        double overallRiskLevel = (customerRiskLevel + areaRiskLevel) / 2;
        String overallRiskText = getOverallRiskText(overallRiskLevel);

        return new RiskAssessment(
                overallRiskLevel,overallRiskText,
                areaRiskLevel, areaRiskText,
                customerRiskLevel, customerRiskText,
                distance, age, yearsExp, days, geoLocation, currentWeather, image);
    }

    private static String getOverallRiskText(double risk)
    {
        if(risk <= .33) return "Low Premium";
        else if(risk <= .66) return "Medium Premium";
        else return "High Premium";
    }

    private static String getRiskText(double riskValue)
    {
        //assuming riskValue 0 -> 1
        return switch ((int) (riskValue * 5)) {
            case 0 -> "Very Low Risk";      // 0 to <0.2
            case 1 -> "Low Risk";           // 0.2 to <0.4
            case 2 -> "Average Risk";       // 0.4 to <0.6
            case 3 -> "High Risk";          // 0.6 to <0.8
            case 4 -> "Very High Risk";     // 0.8 to 1
            default -> "Invalid Risk Value"; // For any value outside 0-1 range
        };
    }

    private double getTempRisk(Weather weather)
    {
        return accidentAssessor.getTempAverage(weather.getCurrent().getTemp_c(), weather.getLocation().getName(), crashRepository);
    }

    private double getCityRisk(Weather weather)
    {
        return accidentAssessor.getCityAverageRisk(weather.getLocation().getName(), crashRepository);
    }


    private static double getUsageRisk(int days, int distance)
    {
        //https://www.kbb.com/car-advice/average-miles-driven-per-year/
        //The average driver in the U.S. drives 39.7 miles per day, according to the most recent Department of Transportation statistics.
        double distanceRisk = 0;
        if      (distance <= 5)     distanceRisk = 0;
        else if (distance <= 10)    distanceRisk = 0.2;
        else if (distance <= 20)    distanceRisk = 0.3;
        else if (distance <= 39)    distanceRisk = 0.5;
        else if (distance <= 50)    distanceRisk = 0.7;
        else if (distance <= 70)    distanceRisk = 0.85;
        else distanceRisk = 1;

        //https://www.autorentalnews.com/10203346/length-of-rental-results-take-first-dip-yoy-since-pandemic
        //average days of rental in cali is 16.2

        double dayRisk = 0;
        if      (days <= 5)  dayRisk = 0.3;
        else if (days <= 10) dayRisk = 0.5;
        else if (days <= 16) dayRisk = 0.75;
        else if (days <= 24) dayRisk = 0.9;
        else dayRisk = 1;

        return (distanceRisk + dayRisk) / 2;
    }

    private static double getIncidentRisk(Incidents incidents, int distance) {
        double areaRiskLevel = 0;
        double distanceMultiplier;
        double maxIncidentRiskLevel = 1.0;
        double baseIncidentRiskLevel = 0.01;

        if (distance <= 15)        distanceMultiplier = 1;
        else if (distance <= 20)  distanceMultiplier = 0.9;
        else if (distance <= 30)  distanceMultiplier = 0.85;
        else if (distance <= 35)  distanceMultiplier = 0.8;
        else if (distance <= 40)  distanceMultiplier = 0.75;
        else if (distance <= 70)  distanceMultiplier = 0.7;
        else                      distanceMultiplier = 0.6;

        if (!incidents.getIncidents().isEmpty()) {
            double totalIncidents = incidents.getIncidents().size();
            double incidentRisk = baseIncidentRiskLevel * totalIncidents;

            // Normalize incident risk to make sure it doesn't exceed the maxIncidentRiskLevel
            double normalizedIncidentRisk = Math.min(incidentRisk, maxIncidentRiskLevel);

            areaRiskLevel = normalizedIncidentRisk * distanceMultiplier;
        }

        // Ensure the area risk level doesn't exceed 1
        return Math.min(areaRiskLevel, 1.0);
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
        else if(age <= 59) return 0.2;
        else if(age >= 65) return 0.4;
        else return 0.5;
    }

}
