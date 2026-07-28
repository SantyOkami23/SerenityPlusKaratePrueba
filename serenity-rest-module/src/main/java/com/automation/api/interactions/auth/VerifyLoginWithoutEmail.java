package com.automation.api.interactions.auth;

import com.automation.api.config.ApiEndpoints;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.rest.interactions.Post;

/**
 * Interacción Screenplay: intenta login solo con password (sin email).
 * Se espera responseCode 400 con mensaje de parámetro faltante.
 */
public class VerifyLoginWithoutEmail implements Interaction {

    private final String password;

    public VerifyLoginWithoutEmail(String password) {
        this.password = password;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to(ApiEndpoints.VERIFY_LOGIN)
                        .with(request -> request.formParam("password", password))
        );
    }

    public static VerifyLoginWithoutEmail withPassword(String password) {
        return new VerifyLoginWithoutEmail(password);
    }
}
