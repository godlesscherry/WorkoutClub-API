package com.example.workoutclub;

import com.example.workoutclub.services.BookingService;
import com.example.workoutclub.services.ClassService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WorkoutClubApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkoutClubApiApplication.class, args);
	}

	/**
	 * Bean for managing class-related operations.
	 * @return an instance of ClassService.
	 */
	@Bean
	public ClassService classService() {
		return new ClassService();
	}

	/**
	 * Bean for managing booking-related operations.
	 * @return an instance of BookingService.
	 */
	@Bean
	public BookingService bookingService() {
		return new BookingService();
	}
}
