package com.example.workoutclub.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Utility class to format responses for API operations.
 */
public class ResponseFormatter {
    private final ObjectMapper objectMapper;

    public ResponseFormatter() {
        this.objectMapper = new ObjectMapper();
        // Register the module to handle Java 8 date/time types
        objectMapper.registerModule(new JavaTimeModule());
        // Ensure dates are serialized as ISO-8601 strings
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    /**
     * Formats a success response.
     *
     * @param message Success message.
     * @param data    Data object to be included in the response.
     * @return Formatted success response as a JSON string.
     */
    public String formatSuccessResponse(String message, Object data) {
        try {
            Response response = new Response("success", message, data);
            return objectMapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            return formatErrorResponse("Failed to serialize response data.");
        }
    }

    /**
     * Formats an error response.
     *
     * @param message Error message.
     * @return Formatted error response as a JSON string.
     */
    public String formatErrorResponse(String message) {
        try {
            Response response = new Response("error", message, null);
            return objectMapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            return "{\"status\":\"error\",\"message\":\"Failed to serialize error response.\"}";
        }
    }

    /**
     * Inner class to represent the structure of a response.
     */
    private static class Response {
        private String status;
        private String message;
        private Object data;

        public Response(String status, String message, Object data) {
            this.status = status;
            this.message = message;
            this.data = data;
        }

        public String getStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }

        public Object getData() {
            return data;
        }
    }
}
