package com.automation.api.interactions.users;

import com.automation.api.config.ApiEndpoints;
import com.automation.api.models.UserAccountRequest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.rest.interactions.Post;

/**
 * Interacción Screenplay: crea una nueva cuenta de usuario con los 17 form params requeridos.
 */
public class CreateUserAccount implements Interaction {

    private final UserAccountRequest userData;

    public CreateUserAccount(UserAccountRequest userData) {
        this.userData = userData;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to(ApiEndpoints.CREATE_ACCOUNT)
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

    public static CreateUserAccount withData(UserAccountRequest userData) {
        return new CreateUserAccount(userData);
    }
}
