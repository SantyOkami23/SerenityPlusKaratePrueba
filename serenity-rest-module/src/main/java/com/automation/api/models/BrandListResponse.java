package com.automation.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Wrapper de respuesta para el endpoint de lista de marcas.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record BrandListResponse(
        @JsonProperty("responseCode") int responseCode,
        @JsonProperty("brands") List<Brand> brands
) {
}
