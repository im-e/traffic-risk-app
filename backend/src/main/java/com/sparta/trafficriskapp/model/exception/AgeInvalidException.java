package com.sparta.trafficriskapp.model.exception;


public class AgeInvalidException extends Exception {
    public AgeInvalidException() {
        super("Age of the driver is not valid, please try again.");
    }
}

