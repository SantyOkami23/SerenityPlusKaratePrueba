package com.automation.api.interactions.users;

import com.automation.api.config.ApiEndpoints;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.rest.interactions.Delete;

/**
 * Interacción Screenplay: elimina una cuenta de usuario usando email y password como form params.
 */
public class DeleteUserAccount implements Interaction {

    private final String email;
    private final String password;

    public DeleteUserAccount(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Delete.from(ApiEndpoints.DELETE_ACCOUNT)
                        .with(request -> request
                                .formParam("email", email)
                                .formParam("password", password))
        );
    }

    public static DeleteUserAccount withCredentials(String email, String password) {
        return new DeleteUserAccount(email, password);
    }
}
