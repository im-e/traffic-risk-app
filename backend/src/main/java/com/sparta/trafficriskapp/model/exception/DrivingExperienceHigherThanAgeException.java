package com.sparta.trafficriskapp.model.exception;

public class DrivingExperienceHigherThanAgeException extends Exception  {
    public DrivingExperienceHigherThanAgeException() {
        super("Driving experience is higher than age, please try again.");
    }
}
