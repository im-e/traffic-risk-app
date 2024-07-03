package com.sparta.trafficriskapp.service;

import com.sparta.trafficriskapp.model.entity.Crash;
import com.sparta.trafficriskapp.model.repository.CrashRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.OptionalDouble;

@Service
public class AccidentAssessor {

    public double getTempAverage(double temp, String city, CrashRepository repository) {
        double minTemp = 12;
        double maxTemp = 22;

        List<Crash> avTemp = repository.findByCityAndTemperatureCBetween(city, minTemp, maxTemp);
        List<Crash> todayTemp;

        if (temp < minTemp) {
            todayTemp = repository.findByCityAndTemperatureCLessThan(city, minTemp);
        } else if (temp > maxTemp) {
            todayTemp = repository.findByCityAndTemperatureCGreaterThan(city, maxTemp);
        } else {
            return 0.5;
        }


        int currentTempCrashes = todayTemp.size();
        int avgTempCrashes = avTemp.size();
        double percentChange = (avgTempCrashes == 0) ? 0 : ((currentTempCrashes - avgTempCrashes) / (double) avgTempCrashes);

        double pc =  (percentChange + 1) / 2;
        if (pc < 0.5) {
            return 0.5;
        }
        if (pc > 0.5) {
            return 0.7;
        }
        else{
            return 0.6;
        }
    }

    public int[] comparePressure(double pressure, String city, CrashRepository repository) {
        double minPress = 75.23;
        double maxPress = 76.17;

        List<Crash> avPress = repository.findByCityAndPressureCmBetween(city, minPress, maxPress);
        List<Crash> todayPress;

        if (pressure < minPress) {
            todayPress = repository.findByCityAndPressureCmLessThan(city, minPress);
        } else if (pressure > maxPress) {
            todayPress = repository.findByCityAndPressureCmGreaterThan(city, maxPress);
        } else {
            return new int[]{0, avPress.size()};
        }

        return new int[]{todayPress.size(), avPress.size()};
    }

    public int[] compareHumidity(double humidity, String city, CrashRepository repository) {
        double minHum = 40;
        double maxHum = 78;

        List<Crash> avHum = repository.findByCityAndHumidityBetween(city, minHum, maxHum);
        List<Crash> todayHum;

        if (humidity < minHum) {
            todayHum = repository.findByCityAndHumidityLessThan(city, minHum);
        } else if (humidity > maxHum) {
            todayHum = repository.findByCityAndHumidityGreaterThan(city, maxHum);
        } else {
            return new int[]{0, avHum.size()};
        }

        return new int[]{todayHum.size(), avHum.size()};
    }

    public double[] compareHour(int hour, String city, CrashRepository repository) {
        List<Crash> accidentsHour = repository.findByCityAndHour(city, hour);
        OptionalDouble avHourOpt = repository.findByCity(city).stream().mapToDouble(Crash::getHour).average();

        double avHour = avHourOpt.isPresent() ? avHourOpt.getAsDouble() : 0;
        double todayHour = accidentsHour.size();
        double percentChange = (todayHour - avHour) / avHour * 100;

        //timeProbability += percentChange;
        return new double[]{avHour, todayHour, percentChange};
    }

    public double[] compareMonth(int month, String city, CrashRepository repository) {
        List<Crash> accidentsMonth = repository.findByCityAndMonth(city, month);
        OptionalDouble avMonthOpt = repository.findByCity(city).stream().mapToDouble(Crash::getMonth).average();

        double avMonth = avMonthOpt.isPresent() ? avMonthOpt.getAsDouble() : 0;
        double todayMonth = accidentsMonth.size();
        double percentChange = (todayMonth - avMonth) / avMonth * 100;

        //timeProbability += percentChange;
        return new double[]{avMonth, todayMonth, percentChange};
    }

    public double[] compareDay(int day, String city, CrashRepository repository) {
        List<Crash> accidentsDay = repository.findByCityAndDay(city, day);
        OptionalDouble avDayOpt = repository.findByCity(city).stream().mapToDouble(Crash::getDay).average();

        double avDay = avDayOpt.isPresent() ? avDayOpt.getAsDouble() : 0;
        double todayDay = accidentsDay.size();
        double percentChange = (todayDay - avDay) / avDay * 100;

        //timeProbability += percentChange;
        return new double[]{avDay, todayDay, percentChange};
    }

    private double compareCityToState(String city, CrashRepository repository) {
        long totalCrashesInState = repository.count();
        long totalCities = repository.findAll().stream().map(Crash::getCity).distinct().count();
        double caliAv = (double) totalCrashesInState / totalCities;

        List<Crash> cityCrashes = repository.findByCity(city);
        double cityAccidents = cityCrashes.size();
        return (cityAccidents - caliAv) / caliAv * 100;


    }

    public double getCityAverageRisk(String city, CrashRepository repository) {

        double cityDifference = compareCityToState(city, repository);
        double cityRisk = (cityDifference + 100) / 200;
        return Math.min(cityRisk, 1); //clamps to 1
    }
}