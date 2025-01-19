package com.example.workoutclub.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents the details of a class offered by the club.
 */
public class ClassDetails {
    @JsonProperty("name")
    private String name;

    @JsonProperty("startDate")
    private LocalDate startDate;

    @JsonProperty("endDate")
    private LocalDate endDate;

    @JsonProperty("startTime")
    private LocalTime startTime;

    @JsonProperty("duration")
    private int duration; // in minutes

    @JsonProperty("capacity")
    private int capacity;

    /**
     * Constructor to initialize a class with its details.
     * @param name Name of the class.
     * @param startDate Start date of the class.
     * @param endDate End date of the class.
     * @param startTime Start time of the class.
     * @param duration Duration of the class in minutes.
     * @param capacity Maximum capacity of the class.
     */
    public ClassDetails(String name, LocalDate startDate, LocalDate endDate, LocalTime startTime, int duration, int capacity) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.duration = duration;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public int getDuration() {
        return duration;
    }

    public int getCapacity() {
        return capacity;
    }
}
