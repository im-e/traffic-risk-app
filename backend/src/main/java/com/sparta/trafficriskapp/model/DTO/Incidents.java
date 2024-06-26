package com.sparta.trafficriskapp.model.DTO;

import java.util.List;

public class Incidents {

    private List<Incident> incidents;

    public List<Incident> getIncidents() {
        return incidents;
    }

    public void setIncidents(List<Incident> incidents) {
        this.incidents = incidents;
    }

    public static class Incident {
        private String type;
        private Properties properties;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Properties getProperties() {
            return properties;
        }

        public void setProperties(Properties properties) {
            this.properties = properties;
        }
    }

    public static class Properties {
        private String id;
        private int iconCategory;
        private int magnitudeOfDelay;
        private String startTime;
        private String endTime;
        private String from;
        private String to;
        private double length;
        private Integer delay;
        private List<String> roadNumbers;
        private String timeValidity;
        private String probabilityOfOccurrence;
        private Integer numberOfReports;
        private String lastReportTime;
        private List<Event> events;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getIconCategory() {
            return iconCategory;
        }

        public void setIconCategory(int iconCategory) {
            this.iconCategory = iconCategory;
        }

        public int getMagnitudeOfDelay() {
            return magnitudeOfDelay;
        }

        public void setMagnitudeOfDelay(int magnitudeOfDelay) {
            this.magnitudeOfDelay = magnitudeOfDelay;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public double getLength() {
            return length;
        }

        public void setLength(double length) {
            this.length = length;
        }

        public Integer getDelay() {
            return delay;
        }

        public void setDelay(Integer delay) {
            this.delay = delay;
        }

        public List<String> getRoadNumbers() {
            return roadNumbers;
        }

        public void setRoadNumbers(List<String> roadNumbers) {
            this.roadNumbers = roadNumbers;
        }

        public String getTimeValidity() {
            return timeValidity;
        }

        public void setTimeValidity(String timeValidity) {
            this.timeValidity = timeValidity;
        }

        public String getProbabilityOfOccurrence() {
            return probabilityOfOccurrence;
        }

        public void setProbabilityOfOccurrence(String probabilityOfOccurrence) {
            this.probabilityOfOccurrence = probabilityOfOccurrence;
        }

        public Integer getNumberOfReports() {
            return numberOfReports;
        }

        public void setNumberOfReports(Integer numberOfReports) {
            this.numberOfReports = numberOfReports;
        }

        public String getLastReportTime() {
            return lastReportTime;
        }

        public void setLastReportTime(String lastReportTime) {
            this.lastReportTime = lastReportTime;
        }

        public List<Event> getEvents() {
            return events;
        }

        public void setEvents(List<Event> events) {
            this.events = events;
        }
    }

    public static class Event {
        private int code;
        private String description;
        private int iconCategory;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getIconCategory() {
            return iconCategory;
        }

        public void setIconCategory(int iconCategory) {
            this.iconCategory = iconCategory;
        }
    }
}
