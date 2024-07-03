package com.sparta.trafficriskapp.model.exception;

public class DrivingExperienceInvalidException extends Exception{
    public DrivingExperienceInvalidException() {
        super("Age is too low for total driving experience, please try again.");
    }
}
