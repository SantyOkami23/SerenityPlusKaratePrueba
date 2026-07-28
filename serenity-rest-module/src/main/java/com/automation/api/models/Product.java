package com.automation.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Representa un producto individual de la API de productos de AutomationExercise.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record Product(
        @JsonProperty("id") int id,
        @JsonProperty("name") String name,
        @JsonProperty("price") String price,
        @JsonProperty("brand") String brand,
        @JsonProperty("category") Category category
) {

    /**
     * Categoría del producto con tipo de usuario anidado.
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Category(
            @JsonProperty("usertype") UserType usertype,
            @JsonProperty("category") String category
    ) {
    }

    /**
     * Tipo de usuario asociado a la categoría (Women, Men, Kids).
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record UserType(
            @JsonProperty("usertype") String usertype
    ) {
    }
}
