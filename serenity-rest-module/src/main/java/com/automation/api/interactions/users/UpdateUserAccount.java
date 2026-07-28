package com.automation.api.interactions.users;

import com.automation.api.config.ApiEndpoints;
import com.automation.api.models.UserAccountRequest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.rest.interactions.Put;

/**
 * Interacción Screenplay: actualiza una cuenta de usuario existente con los 17 form params.
 */
public class UpdateUserAccount implements Interaction {

    private final UserAccountRequest userData;

    public UpdateUserAccount(UserAccountRequest userData) {
        this.userData = userData;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Put.to(ApiEndpoints.UPDATE_ACCOUNT)
                        .with(request -> request
                                .formParam("name", userData.getName())
                                .formParam("email", userData.getEmail())
                                .formParam("password", userData.getPassword())
                                .formParam("title", userData.getTitle())
                                .formParam("birth_date", userData.getBirthDate())
                                .formParam("birth_month", userData.getBirthMonth())
                                .formParam("birth_year", userData.getBirthYear())
                                .formParam("firstname", userData.getFirstname())
                                .formParam("lastname", userData.getLastname())
                                .formParam("company", userData.getCompany())
                                .formParam("address1", userData.getAddress1())
                                .formParam("address2", userData.getAddress2())
                                .formParam("country", userData.getCountry())
                                .formParam("zipcode", userData.getZipcode())
                                .formParam("state", userData.getState())
                                .formParam("city", userData.getCity())
                                .formParam("mobile_number", userData.getMobileNumber()))
        );
    }

    public static UpdateUserAccount withData(UserAccountRequest userData) {
        return new UpdateUserAccount(userData);
    }
}
