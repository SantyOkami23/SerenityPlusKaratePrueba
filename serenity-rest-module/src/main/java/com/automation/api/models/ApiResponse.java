package com.automation.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Modelo genérico de respuesta de la API de AutomationExercise.
 * Todos los endpoints devuelven un responseCode en el body JSON (el HTTP status siempre es 200).
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record ApiResponse(
        @JsonProperty("responseCode") int responseCode,
        @JsonProperty("message") String message
) {
}
