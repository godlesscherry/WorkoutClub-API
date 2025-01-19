package com.example.workoutclub.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClassDetailsTest {

    @Test
    public void testClassDetailsConstructorAndGetters() {
        String name = "Yoga";
        LocalDate startDate = LocalDate.now().plusDays(1);
        LocalDate endDate = LocalDate.now().plusDays(10);
        LocalTime startTime = LocalTime.parse("10:00");
        int duration = 60;
        int capacity = 20;

        ClassDetails classDetails = new ClassDetails(name, startDate, endDate, startTime, duration, capacity);

        assertEquals(name, classDetails.getName());
        assertEquals(startDate, classDetails.getStartDate());
        assertEquals(endDate, classDetails.getEndDate());
        assertEquals(startTime, classDetails.getStartTime());
        assertEquals(duration, classDetails.getDuration());
        assertEquals(capacity, classDetails.getCapacity());
    }
}