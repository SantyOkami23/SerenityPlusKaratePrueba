package com.automation.api.interactions.auth;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.rest.interactions.Post;

/**
 * Screenplay Interaction: attempts login with only password (no email).
 * Expected to return responseCode 400 with missing parameter message.
 */
public class VerifyLoginWithoutEmail implements Interaction {

    private final String password;

    public VerifyLoginWithoutEmail(String password) {
        this.password = password;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to("/api/verifyLogin")
                        .with(request -> request.formParam("password", password))
        );
    }

    public static VerifyLoginWithoutEmail withPassword(String password) {
        return new VerifyLoginWithoutEmail(password);
    }
}
