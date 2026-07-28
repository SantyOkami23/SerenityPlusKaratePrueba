package com.automation.api.interactions.auth;

import com.automation.api.config.ApiEndpoints;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.rest.interactions.Post;

/**
 * Interacción Screenplay: intenta login sin email ni password.
 * Se espera responseCode 400 con mensaje de parámetro faltante.
 */
public class VerifyLoginWithoutParams implements Interaction {

    public VerifyLoginWithoutParams() {
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to(ApiEndpoints.VERIFY_LOGIN)
                        .with(request -> request
                                .header("Content-Type", "application/x-www-form-urlencoded"))
        );
    }

    public static VerifyLoginWithoutParams perform() {
        return new VerifyLoginWithoutParams();
    }
}
