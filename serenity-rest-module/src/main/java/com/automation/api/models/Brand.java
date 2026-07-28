package com.automation.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a single brand from the AutomationExercise brands API.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record Brand(
        @JsonProperty("id") int id,
        @JsonProperty("brand") String brand
) {
}
