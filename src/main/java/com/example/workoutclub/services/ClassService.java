package com.example.workoutclub.services;

import com.example.workoutclub.models.ClassDetails;
import com.example.workoutclub.utils.ResponseFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * Service to manage class-related operations.
 */
@Service
public class ClassService {
    private final ConcurrentMap<String, List<ClassDetails>> classes = new ConcurrentHashMap<>();
    private final ResponseFormatter responseFormatter;
    @Autowired
    public ClassService(ResponseFormatter responseFormatter) {
        this.responseFormatter = responseFormatter;
    }
    /**
     * Adds a new class to the system.
     *
     * @param classDetails Details of the class to be added.
     * @return Formatted success message or error response.
     */
    public String addClass(ClassDetails classDetails) {
        if (classDetails.getCapacity() < 1) {
            return responseFormatter.formatErrorResponse("Capacity must be at least 1.");
        }
        if (classDetails.getEndDate().isBefore(classDetails.getStartDate())) {
            return responseFormatter.formatErrorResponse("End date must be after start date.");
        }
        classes.computeIfAbsent(classDetails.getName(), k -> new ArrayList<>()).add(classDetails);
        return responseFormatter.formatSuccessResponse("Class added successfully.", classDetails);
    }


    /**
     * Retrieves all classes or filters them by optional criteria.
     *
     * @param name Optional filter for class name.
     * @param date Optional filter for a specific date.
     * @return Formatted success response with a list of classes matching the filters.
     */
    public String searchClasses(String name, LocalDate date) {
        List<ClassDetails> filteredClasses = classes.values().stream()
                .flatMap(List::stream)
                .filter(c -> (name == null || c.getName().equalsIgnoreCase(name)) &&
                        (date == null || (!date.isBefore(c.getStartDate()) && !date.isAfter(c.getEndDate()))))
                .collect(Collectors.toList());

        if (filteredClasses.isEmpty()) {
            return responseFormatter.formatErrorResponse("No classes found matching the criteria.");
        }

        return responseFormatter.formatSuccessResponse("Classes retrieved successfully.", filteredClasses);
    }

}
