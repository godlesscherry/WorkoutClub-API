package com.example.workoutclub.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResponseFormatterTest {

    private ResponseFormatter responseFormatter;

    @BeforeEach
    public void setUp() {
        responseFormatter = new ResponseFormatter();
    }

    @Test
    public void testFormatSuccessResponse() {
        String message = "Operation successful";
        Object data = null;
        String expectedResponse = "{\"status\":\"success\",\"message\":\"Operation successful\",\"data\":null}";

        String actualResponse = responseFormatter.formatSuccessResponse(message, data);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testFormatErrorResponse() {
        String message = "Operation failed";
        String expectedResponse = "{\"status\":\"error\",\"message\":\"Operation failed\",\"data\":null}";

        String actualResponse = responseFormatter.formatErrorResponse(message);

        assertEquals(expectedResponse, actualResponse);
    }
}