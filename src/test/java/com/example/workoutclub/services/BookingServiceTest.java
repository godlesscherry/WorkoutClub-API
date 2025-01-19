package com.example.workoutclub.services;

import com.example.workoutclub.models.Booking;
import com.example.workoutclub.utils.ResponseFormatter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BookingServiceTest {

    @Mock
    private ResponseFormatter responseFormatter;

    @InjectMocks
    private BookingService bookingService;

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
    public void testAddBooking_Success() {
        Booking booking = new Booking("John Doe", "Pilates", LocalDate.now().plusDays(1));
        when(responseFormatter.formatSuccessResponse(anyString(), any())).thenReturn("Booking created successfully.");

        String response = bookingService.addBooking(booking);

        assertEquals("Booking created successfully.", response);
        verify(responseFormatter, times(1)).formatSuccessResponse(anyString(), any());
    }

    @Test
    public void testAddBooking_ParticipationDateInPast() {
        Booking booking = new Booking("John Doe", "Pilates", LocalDate.now().minusDays(1));
        when(responseFormatter.formatErrorResponse(anyString())).thenReturn("Participation date must be in the future.");

        String response = bookingService.addBooking(booking);

        assertEquals("Participation date must be in the future.", response);
        verify(responseFormatter, times(1)).formatErrorResponse(anyString());
    }

   @Test
    public void testAddBooking_ClassCapacityExceeded() {
    Booking booking = new Booking("John Doe", "Pilates", LocalDate.now().plusDays(1));
    for (int i = 0; i < 10; i++) {
        bookingService.addBooking(new Booking("Member " + i, "Pilates", LocalDate.now().plusDays(1)));
    }
    when(responseFormatter.formatErrorResponse(anyString())).thenReturn("Class capacity exceeded for the given date.");

    String response = bookingService.addBooking(booking);

    assertEquals("Class capacity exceeded for the given date.", response);
    verify(responseFormatter, times(1)).formatErrorResponse(anyString());
    }

    @Test
    public void testSearchBookings_Success() {
    Booking booking1 = new Booking("John Doe", "Pilates", LocalDate.now().plusDays(1));
    Booking booking2 = new Booking("John Doe", "Yoga", LocalDate.now().plusDays(2));
    bookingService.addBooking(booking1);
    bookingService.addBooking(booking2);
    clearInvocations(responseFormatter);
    when(responseFormatter.formatSuccessResponse(anyString(), any())).thenReturn("Bookings retrieved successfully.");

    String response = bookingService.searchBookings("John Doe", null, null);

    assertEquals("Bookings retrieved successfully.", response);
    verify(responseFormatter, times(1)).formatSuccessResponse(anyString(), any());
    }

    @Test
    public void testSearchBookings_NoBookingsFound() {
        when(responseFormatter.formatErrorResponse(anyString())).thenReturn("No bookings found matching the criteria.");

        String response = bookingService.searchBookings("John Doe", null, null);

        assertEquals("No bookings found matching the criteria.", response);
        verify(responseFormatter, times(1)).formatErrorResponse(anyString());
    }
}