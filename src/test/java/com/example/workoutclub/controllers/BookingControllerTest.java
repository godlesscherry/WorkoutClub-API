package com.example.workoutclub.controllers;

import com.example.workoutclub.models.Booking;
import com.example.workoutclub.services.BookingService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class BookingControllerTest {

    @Mock
    private BookingService bookingService;

    @InjectMocks
    private BookingController bookingController;

    private AutoCloseable closeable;

    @BeforeEach
    public void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void close() throws Exception {
        closeable.close();
    }

    @Test
    public void testBookClass_Success() {
        Booking booking = new Booking("John Doe", "Pilates", LocalDate.now().plusDays(1));
        when(bookingService.addBooking(any(Booking.class))).thenReturn("Booking created successfully.");

        ResponseEntity<String> response = bookingController.bookClass(booking);

       assertEquals(201, response.getStatusCode().value());
        assertEquals("Booking created successfully.", response.getBody());
    }

    @Test
    public void testBookClass_Failure() {
        Booking booking = new Booking("John Doe", "Pilates", LocalDate.now().minusDays(1));
        when(bookingService.addBooking(any(Booking.class))).thenReturn("error: Participation date must be in the future.");

        ResponseEntity<String> response = bookingController.bookClass(booking);

        assertEquals(400, response.getStatusCode().value());
        assertEquals("error: Participation date must be in the future.", response.getBody());
    }

    @Test
    public void testSearchBookings_Success() {
        when(bookingService.searchBookings(null, null, null)).thenReturn("Bookings retrieved successfully.");

        ResponseEntity<String> response = bookingController.searchBookings(null, null, null);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Bookings retrieved successfully.", response.getBody());
    }

    @Test
    public void testSearchBookings_Failure() {
        when(bookingService.searchBookings(null, null, null)).thenReturn("error: No bookings found.");

        ResponseEntity<String> response = bookingController.searchBookings(null, null, null);

        assertEquals(400, response.getStatusCode().value());
        assertEquals("error: No bookings found.", response.getBody());
    }
}