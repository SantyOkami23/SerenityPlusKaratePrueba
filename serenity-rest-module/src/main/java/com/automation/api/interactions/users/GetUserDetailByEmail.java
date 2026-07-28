package com.automation.api.interactions.users;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.rest.interactions.Get;

/**
 * Screenplay Interaction: retrieves user detail by email via query parameter.
 */
public class GetUserDetailByEmail implements Interaction {

    private final String email;

    public GetUserDetailByEmail(String email) {
        this.email = email;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource("/api/getUserDetailByEmail")
                        .with(request -> request.queryParam("email", email))
        );
    }

    public static GetUserDetailByEmail withEmail(String email) {
        return new GetUserDetailByEmail(email);
    }
}
