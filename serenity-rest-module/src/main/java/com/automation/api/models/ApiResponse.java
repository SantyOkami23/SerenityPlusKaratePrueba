package com.automation.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Generic API response model matching AutomationExercise response structure.
 * All endpoints return a responseCode in the JSON body (HTTP status is always 200).
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record ApiResponse(
        @JsonProperty("responseCode") int responseCode,
        @JsonProperty("message") String message
) {
}
