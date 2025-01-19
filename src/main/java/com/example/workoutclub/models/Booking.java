package com.example.workoutclub.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

/**
 * Represents a booking made by a member for a specific class.
 */
public class Booking {
    @JsonProperty("memberName")
    private String memberName;

    @JsonProperty("className")
    private String className;

    @JsonProperty("participationDate")
    private LocalDate participationDate;

    /**
     * Constructor to initialize a booking with its details.
     * @param memberName Name of the member making the booking.
     * @param className Name of the class being booked.
     * @param participationDate Date of participation in the class.
     */
    public Booking(String memberName, String className, LocalDate participationDate) {
        this.memberName = memberName;
        this.className = className;
        this.participationDate = participationDate;
    }

    public String getMemberName() {
        return memberName;
    }

    public String getClassName() {
        return className;
    }

    public LocalDate getParticipationDate() {
        return participationDate;
    }
}
