package com.automation.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Wrapper de respuesta para el endpoint de lista de productos.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record ProductListResponse(
        @JsonProperty("responseCode") int responseCode,
        @JsonProperty("products") List<Product> products
) {
}
