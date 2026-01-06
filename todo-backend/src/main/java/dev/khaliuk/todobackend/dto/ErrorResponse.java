package dev.khaliuk.todobackend.dto;

import java.util.Map;

public record ErrorResponse(String message, Map<String, String> errors) {
    public static ErrorResponse ofErrors(Map<String, String> errors) {
        return new ErrorResponse("Validation failed", errors);
    }
}
