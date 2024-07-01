package com.sparta.trafficriskapp.model.repository;

import com.sparta.trafficriskapp.model.entity.Crash;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.DayOfWeek;
import java.util.List;

public interface CrashRepository extends JpaRepository<Crash, String> {

    List<Crash> findByCity(String city);
    List<Crash> findByCityAndTemperatureCBetween(String city, double minTemp, double maxTemp);
    List<Crash> findByCityAndTemperatureCLessThan(String city, double minTemp);
    List<Crash> findByCityAndTemperatureCGreaterThan(String city, double maxTemp);
    List<Crash> findByCityAndPressureCmBetween(String city, double minPress, double maxPress);
    List<Crash> findByCityAndPressureCmLessThan(String city, double minPress);
    List<Crash> findByCityAndPressureCmGreaterThan(String city, double maxPress);
    List<Crash> findByCityAndHumidityBetween(String city, double minHum, double maxHum);
    List<Crash> findByCityAndHumidityLessThan(String city, double minHum);
    List<Crash> findByCityAndHumidityGreaterThan(String city, double maxHum);
    List<Crash> findByCityAndHour(String city, double minHour);
    List<Crash> findByCityAndMonth(String city, double month);
    List<Crash> findByCityAndDay(String city, double day);
}