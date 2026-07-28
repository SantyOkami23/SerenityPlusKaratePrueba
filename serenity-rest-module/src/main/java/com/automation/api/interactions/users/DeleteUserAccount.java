package com.automation.api.interactions.users;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.rest.interactions.Delete;

/**
 * Screenplay Interaction: deletes a user account using email and password form params.
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
                Delete.from("/api/deleteAccount")
                        .with(request -> request
                                .formParam("email", email)
                                .formParam("password", password))
        );
    }

    public static DeleteUserAccount withCredentials(String email, String password) {
        return new DeleteUserAccount(email, password);
    }
}
