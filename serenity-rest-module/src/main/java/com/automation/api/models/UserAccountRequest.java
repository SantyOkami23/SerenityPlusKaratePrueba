package com.automation.api.models;

import lombok.Builder;
import lombok.Getter;

/**
 * Request model for creating (API 11) and updating (API 13) user accounts.
 * Contains all 17 required form parameters.
 */
@Getter
@Builder
public class UserAccountRequest {

    private final String name;
    private final String email;
    private final String password;
    private final String title;
    private final String birthDate;
    private final String birthMonth;
    private final String birthYear;
    private final String firstname;
    private final String lastname;
    private final String company;
    private final String address1;
    private final String address2;
    private final String country;
    private final String zipcode;
    private final String state;
    private final String city;
    private final String mobileNumber;
}
