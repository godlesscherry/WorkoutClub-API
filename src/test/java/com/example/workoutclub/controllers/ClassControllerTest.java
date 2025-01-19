package com.example.workoutclub.controllers;

import com.example.workoutclub.models.ClassDetails;
import com.example.workoutclub.services.ClassService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ClassControllerTest {

    @Mock
    private ClassService classService;

    @InjectMocks
    private ClassController classController;

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
    public void testCreateClass_Success() {
        ClassDetails classDetails = new ClassDetails("Yoga", LocalDate.now().plusDays(1), LocalDate.now().plusDays(10), LocalTime.parse("10:00"), 60, 20);
        when(classService.addClass(any(ClassDetails.class))).thenReturn("Class added successfully.");

        ResponseEntity<String> response = classController.createClass(classDetails);

        assertEquals(201, response.getStatusCode().value());
        assertEquals("Class added successfully.", response.getBody());
    }

    @Test
    public void testCreateClass_Failure() {
        ClassDetails classDetails = new ClassDetails("Yoga", LocalDate.now().plusDays(1), LocalDate.now().plusDays(10), LocalTime.parse("10:00"), 60, 0);
        when(classService.addClass(any(ClassDetails.class))).thenReturn("error: Capacity must be at least 1.");
        ResponseEntity<String> response = classController.createClass(classDetails);

        assertEquals(400, response.getStatusCode().value());
        assertEquals("error: Capacity must be at least 1.", response.getBody());
    }

    @Test
    public void testGetClasses_Success() {
        when(classService.searchClasses(null, null)).thenReturn("Classes retrieved successfully.");

        ResponseEntity<String> response = classController.getClasses(null, null);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Classes retrieved successfully.", response.getBody());
    }

    @Test
    public void testGetClasses_Failure() {
        when(classService.searchClasses(null, null)).thenReturn("error: No classes found.");

        ResponseEntity<String> response = classController.getClasses(null, null);

        assertEquals(400, response.getStatusCode().value());
        assertEquals("error: No classes found.", response.getBody());
    }
}