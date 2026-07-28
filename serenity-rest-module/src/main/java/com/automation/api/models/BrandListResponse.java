package com.automation.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Response wrapper for the brands list endpoint.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record BrandListResponse(
        @JsonProperty("responseCode") int responseCode,
        @JsonProperty("brands") List<Brand> brands
) {
}
