package com.sparta.trafficriskapp.model.exception;


import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler
    public void handleAgeInvalidException(AgeInvalidException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

    @ExceptionHandler
    public void handleDrivingExpException(DrivingExperienceHigherThanAgeException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }


    @ExceptionHandler
    public void handleDrivingExp2Exception(DrivingExperienceInvalidException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }
}
