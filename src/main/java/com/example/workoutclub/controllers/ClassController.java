package com.example.workoutclub.controllers;

import com.example.workoutclub.models.ClassDetails;
import com.example.workoutclub.services.ClassService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * Controller to handle APIs related to class operations.
 */
@RestController
@RequestMapping("/api/classes")
public class ClassController {
    private final ClassService classService;

    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    /**
     * Endpoint to create a new class.
     * @param classDetails Details of the class to be created.
     * @return Response indicating success or failure.
     */
    @PostMapping
    public ResponseEntity<String> createClass(@RequestBody ClassDetails classDetails) {
        String response = classService.addClass(classDetails);
        if (response.contains("error")) {
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.status(201).body(response);
    }

    /**
     * Endpoint to retrieve all classes or filter by optional criteria.
     * @param name Optional filter for class name.
     * @param date Optional filter for class date.
     * @return Response containing the list of classes or an error message.
     */
    @GetMapping
    public ResponseEntity<String> getClasses(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) LocalDate date) {
        String response = classService.searchClasses(name, date);
        if (response.contains("error")) {
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }
}
