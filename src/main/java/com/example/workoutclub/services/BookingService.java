package com.example.workoutclub.services;

import com.example.workoutclub.models.Booking;
import com.example.workoutclub.utils.ResponseFormatter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service to manage booking-related operations.
 */
@Service
public class BookingService {
    private final List<Booking> bookings = new ArrayList<>();
    private final ResponseFormatter responseFormatter;

    public BookingService( ResponseFormatter responseFormatter) {
        this.responseFormatter = responseFormatter;
    }

    /**
     * Adds a new booking to the system.
     *
     * @param booking Details of the booking to be added.
     * @return Formatted success or error message.
     */
    public String addBooking(Booking booking) {
        if (booking.getParticipationDate().isBefore(LocalDate.now())) {
            return responseFormatter.formatErrorResponse("Participation date must be in the future.");
        }

        long count = bookings.stream()
                .filter(b -> b.getClassName().equals(booking.getClassName()) &&
                        b.getParticipationDate().equals(booking.getParticipationDate()))
                .count();

        // Assuming a fixed capacity for simplicity (e.g., 10)
        if (count >= 10) {
            return responseFormatter.formatErrorResponse("Class capacity exceeded for the given date.");
        }

        bookings.add(booking);
        return responseFormatter.formatSuccessResponse("Booking created successfully.", booking);
    }

    /**
     * Searches for bookings based on member name and/or date range.
     *
     * @param memberName Optional filter for member name.
     * @param startDate  Optional filter for start date.
     * @param endDate    Optional filter for end date.
     * @return Formatted success or error response with a list of bookings matching the search criteria.
     */
    public String searchBookings(String memberName, LocalDate startDate, LocalDate endDate) {
        List<Booking> filteredBookings = bookings.stream()
                .filter(b -> (memberName == null || b.getMemberName().equalsIgnoreCase(memberName)) &&
                        (startDate == null || !b.getParticipationDate().isBefore(startDate)) &&
                        (endDate == null || !b.getParticipationDate().isAfter(endDate)))
                .collect(Collectors.toList());

        if (filteredBookings.isEmpty()) {
            return responseFormatter.formatErrorResponse("No bookings found matching the criteria.");
        }

        return responseFormatter.formatSuccessResponse("Bookings retrieved successfully.", filteredBookings);
    }
}
