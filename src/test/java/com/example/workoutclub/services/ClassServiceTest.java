package com.example.workoutclub.services;

import com.example.workoutclub.models.ClassDetails;
import com.example.workoutclub.utils.ResponseFormatter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ClassServiceTest {

    @Mock
    private ResponseFormatter responseFormatter;

    @InjectMocks
    private ClassService classService;

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
    public void testAddClass_Success() {
        ClassDetails classDetails = new ClassDetails("Yoga", LocalDate.now().plusDays(1), LocalDate.now().plusDays(10), LocalTime.parse("10:00"), 60, 20);
        when(responseFormatter.formatSuccessResponse(anyString(), any())).thenReturn("Class added successfully.");

        String response = classService.addClass(classDetails);

        assertEquals("Class added successfully.", response);
        verify(responseFormatter, times(1)).formatSuccessResponse(anyString(), any());
    }

    @Test
    public void testAddClass_CapacityLessThanOne() {
        ClassDetails classDetails = new ClassDetails("Yoga", LocalDate.now().plusDays(1), LocalDate.now().plusDays(10), LocalTime.parse("10:00"), 60, 0);
        when(responseFormatter.formatErrorResponse(anyString())).thenReturn("Capacity must be at least 1.");

        String response = classService.addClass(classDetails);

        assertEquals("Capacity must be at least 1.", response);
        verify(responseFormatter, times(1)).formatErrorResponse(anyString());
    }

    @Test
    public void testSearchClasses_Success() {
    ClassDetails classDetails = new ClassDetails("Yoga", LocalDate.now().plusDays(1), LocalDate.now().plusDays(10), LocalTime.parse("10:00"), 60, 20);
    classService.addClass(classDetails);
    reset(responseFormatter); // Reset the mock to clear previous invocations
    when(responseFormatter.formatSuccessResponse(anyString(), any())).thenReturn("Classes retrieved successfully.");

    String response = classService.searchClasses("Yoga", null);

    assertEquals("Classes retrieved successfully.", response);
    verify(responseFormatter, times(1)).formatSuccessResponse(anyString(), any());
    }

    @Test
    public void testSearchClasses_NoClassesFound() {
        when(responseFormatter.formatErrorResponse(anyString())).thenReturn("No classes found matching the criteria.");

        String response = classService.searchClasses("Yoga", null);

        assertEquals("No classes found matching the criteria.", response);
        verify(responseFormatter, times(1)).formatErrorResponse(anyString());
    }

    @Test
    public void testCreateMultipleClasses_Success() {
        ClassDetails classDetails1 = new ClassDetails("Yoga", LocalDate.now().plusDays(1), LocalDate.now().plusDays(10), LocalTime.parse("10:00"), 60, 20);
        ClassDetails classDetails2 = new ClassDetails("Pilates", LocalDate.now().plusDays(2), LocalDate.now().plusDays(11), LocalTime.parse("11:00"), 60, 20);
        when(responseFormatter.formatSuccessResponse(anyString(), any())).thenReturn("Classes added successfully.");

        String response1 = classService.addClass(classDetails1);
        String response2 = classService.addClass(classDetails2);

        assertEquals("Classes added successfully.", response1);
        assertEquals("Classes added successfully.", response2);
        verify(responseFormatter, times(2)).formatSuccessResponse(anyString(), any());
    }

    @Test
    public void testCreateClassWithSameName_Success() {
        ClassDetails classDetails1 = new ClassDetails("Yoga", LocalDate.now().plusDays(1), LocalDate.now().plusDays(10), LocalTime.parse("10:00"), 60, 20);
        ClassDetails classDetails2 = new ClassDetails("Yoga", LocalDate.now().plusDays(2), LocalDate.now().plusDays(11), LocalTime.parse("11:00"), 60, 20);
        when(responseFormatter.formatSuccessResponse(anyString(), any())).thenReturn("Classes added successfully.");

        String response1 = classService.addClass(classDetails1);
        String response2 = classService.addClass(classDetails2);

        assertEquals("Classes added successfully.", response1);
        assertEquals("Classes added successfully.", response2);
        verify(responseFormatter, times(2)).formatSuccessResponse(anyString(), any());
    }
}