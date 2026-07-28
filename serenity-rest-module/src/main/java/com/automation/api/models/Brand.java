package com.automation.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Representa una marca individual de la API de marcas de AutomationExercise.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record Brand(
        @JsonProperty("id") int id,
        @JsonProperty("brand") String brand
) {
}
