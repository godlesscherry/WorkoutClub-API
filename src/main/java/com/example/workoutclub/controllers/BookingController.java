package com.example.workoutclub.controllers;

import com.example.workoutclub.models.Booking;
import com.example.workoutclub.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

/**
 * Controller to handle APIs related to booking operations.
 */
@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    @Autowired
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    /**
     * Endpoint to book a class for a member.
     * @param booking Details of the booking.
     * @return Response indicating success or failure.
     */
    @PostMapping
    public ResponseEntity<String> bookClass(@RequestBody Booking booking) {
        String response = bookingService.addBooking(booking);
        if (response.contains("error")) {
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.status(201).body(response);
    }

    /**
     * Endpoint to search for bookings.
     * @param memberName Optional filter for member name.
     * @param startDate Optional filter for start date.
     * @param endDate Optional filter for end date.
     * @return Response containing the list of bookings or an error message.
     */
    @GetMapping
    public ResponseEntity<String> searchBookings(
            @RequestParam(required = false) String memberName,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate) {
        String response = bookingService.searchBookings(memberName, startDate, endDate);
        if (response.contains("error")) {
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }
}
