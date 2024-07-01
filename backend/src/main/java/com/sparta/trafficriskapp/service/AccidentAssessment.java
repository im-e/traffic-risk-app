package com.sparta.trafficriskapp.service;

import com.sparta.trafficriskapp.model.entity.Crash;
import com.sparta.trafficriskapp.model.repository.CrashRepository;

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

public class AccidentAssessment {
    private String city;
    private List<Crash> crashData;
    private double timeProbability;
    private double cityDifference;

    public AccidentAssessment(String city, CrashRepository repository) {
        this.city = city;
        this.crashData = repository.findByCity(city);
        this.timeProbability = 0;
        this.cityDifference = 0;
    }

    public int[] compareTemp(double temp, CrashRepository repository) {
        double minTemp = 12;
        double maxTemp = 22;

        List<Crash> avTemp = repository.findByCityAndTemperatureCBetween(city, minTemp, maxTemp);
        List<Crash> todayTemp;

        if (temp < minTemp) {
            todayTemp = repository.findByCityAndTemperatureCLessThan(city, minTemp);
        } else if (temp > maxTemp) {
            todayTemp = repository.findByCityAndTemperatureCGreaterThan(city, maxTemp);
        } else {
            return new int[]{0, avTemp.size()};
        }

        return new int[]{todayTemp.size(), avTemp.size()};
    }

    public int[] comparePressure(double pressure, CrashRepository repository) {
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

    public int[] compareHumidity(double humidity, CrashRepository repository) {
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

    public double[] compareHour(int hour, CrashRepository repository) {
        List<Crash> accidentsHour = repository.findByCityAndHour(city, hour);
        OptionalDouble avHourOpt = repository.findByCity(city).stream().mapToDouble(Crash::getHour).average();

        double avHour = avHourOpt.isPresent() ? avHourOpt.getAsDouble() : 0;
        double todayHour = accidentsHour.size();
        double percentChange = (todayHour - avHour) / avHour * 100;

        timeProbability += percentChange;
        return new double[]{avHour, todayHour, percentChange};
    }

    public double[] compareMonth(int month, CrashRepository repository) {
        List<Crash> accidentsMonth = repository.findByCityAndMonth(city, month);
        OptionalDouble avMonthOpt = repository.findByCity(city).stream().mapToDouble(Crash::getMonth).average();

        double avMonth = avMonthOpt.isPresent() ? avMonthOpt.getAsDouble() : 0;
        double todayMonth = accidentsMonth.size();
        double percentChange = (todayMonth - avMonth) / avMonth * 100;

        timeProbability += percentChange;
        return new double[]{avMonth, todayMonth, percentChange};
    }

    public double[] compareDay(int day, CrashRepository repository) {
        List<Crash> accidentsDay = repository.findByCityAndDay(city, day);
        OptionalDouble avDayOpt = repository.findByCity(city).stream().mapToDouble(Crash::getDay).average();

        double avDay = avDayOpt.isPresent() ? avDayOpt.getAsDouble() : 0;
        double todayDay = accidentsDay.size();
        double percentChange = (todayDay - avDay) / avDay * 100;

        timeProbability += percentChange;
        return new double[]{avDay, todayDay, percentChange};
    }

    public double[] compareCityToState(CrashRepository repository) {
        long totalCrashesInState = repository.count();
        long totalCities = repository.findAll().stream().map(Crash::getCity).distinct().count();
        double caliAv = (double) totalCrashesInState / totalCities;

        double cityAccidents = crashData.size();
        cityDifference = (cityAccidents - caliAv) / caliAv * 100;

        return new double[]{caliAv, cityAccidents, cityDifference};
    }

    public void getProbabilities(Optional<Integer> hour, Optional<Integer> day, Optional<Integer> month, CrashRepository repository) {
        day.ifPresent(d -> compareDay(d, repository));
        hour.ifPresent(h -> compareHour(h, repository));
        month.ifPresent(m -> compareMonth(m, repository));

        if (timeProbability < 0) {
            System.out.printf("Based on the time and date, today your chance of an accident is %.2f%% less likely than normal in %s%n", Math.abs(timeProbability), city);
        } else if (timeProbability > 0) {
            System.out.printf("Based on the time and date, today your chance of an accident is %.2f%% more likely than normal in %s%n", timeProbability, city);
        } else {
            System.out.printf("Based on the time and date, today you have an average chance of an accident in %s%n", city);
        }
        timeProbability = 0;

        double[] cityProb = compareCityToState(repository);
        if (cityDifference < 0) {
            System.out.printf("%s is %.2f%% less likely to have an accident than average in California%n", city, Math.abs(cityDifference));
        } else if (cityDifference > 0) {
            System.out.printf("%s is %.2f%% more likely to have an accident than average in California%n", city, cityDifference);
        } else {
            System.out.printf("%s has the same accident risk as average in California%n", city);
        }
        cityDifference = 0;
    }
}