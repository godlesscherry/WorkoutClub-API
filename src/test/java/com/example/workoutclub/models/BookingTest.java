package com.example.workoutclub.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookingTest {

    @Test
    public void testBookingConstructorAndGetters() {
        String memberName = "John Doe";
        String className = "Pilates";
        LocalDate participationDate = LocalDate.now().plusDays(1);

        Booking booking = new Booking(memberName, className, participationDate);

        assertEquals(memberName, booking.getMemberName());
        assertEquals(className, booking.getClassName());
        assertEquals(participationDate, booking.getParticipationDate());
    }
}