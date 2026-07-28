package com.automation.api.interactions.users;

import com.automation.api.config.ApiEndpoints;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.rest.interactions.Get;

/**
 * Interacción Screenplay: obtiene el detalle de un usuario por su email vía query param.
 */
public class GetUserDetailByEmail implements Interaction {

    private final String email;

    public GetUserDetailByEmail(String email) {
        this.email = email;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource(ApiEndpoints.GET_USER_DETAIL)
                        .with(request -> request.queryParam("email", email))
        );
    }

    public static GetUserDetailByEmail withEmail(String email) {
        return new GetUserDetailByEmail(email);
    }
}
