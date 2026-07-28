package com.automation.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Response wrapper for the products list endpoint.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record ProductListResponse(
        @JsonProperty("responseCode") int responseCode,
        @JsonProperty("products") List<Product> products
) {
}
