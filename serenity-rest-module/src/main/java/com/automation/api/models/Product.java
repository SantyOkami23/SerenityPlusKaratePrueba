package com.automation.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a single product from the AutomationExercise products API.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record Product(
        @JsonProperty("id") int id,
        @JsonProperty("name") String name,
        @JsonProperty("price") String price,
        @JsonProperty("brand") String brand,
        @JsonProperty("category") Category category
) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Category(
            @JsonProperty("usertype") UserType usertype,
            @JsonProperty("category") String category
    ) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record UserType(
            @JsonProperty("usertype") String usertype
    ) {
    }
}
