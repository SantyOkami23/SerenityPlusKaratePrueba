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
        java.util.Map<String, String> map = new java.util.HashMap<>();
        map.put("name", userData.getName());
        map.put("email", userData.getEmail());
        map.put("password", userData.getPassword());
        map.put("title", userData.getTitle());
        map.put("birth_date", userData.getBirthDate());
        map.put("birth_month", userData.getBirthMonth());
        map.put("birth_year", userData.getBirthYear());
        map.put("firstname", userData.getFirstname());
        map.put("lastname", userData.getLastname());
        map.put("company", userData.getCompany());
        map.put("address1", userData.getAddress1());
        map.put("address2", userData.getAddress2());
        map.put("country", userData.getCountry());
        map.put("zipcode", userData.getZipcode());
        map.put("state", userData.getState());
        map.put("city", userData.getCity());
        map.put("mobile_number", userData.getMobileNumber());

        actor.attemptsTo(
                Put.to(ApiEndpoints.UPDATE_ACCOUNT)
                        .with(request -> request
                                .log().all()
                                .header("Content-Type", "application/x-www-form-urlencoded")
                                .body(com.automation.api.utils.UrlEncoderUtils.encodeMap(map)))
        );
    }

    public static UpdateUserAccount withData(UserAccountRequest userData) {
        return new UpdateUserAccount(userData);
    }
}
