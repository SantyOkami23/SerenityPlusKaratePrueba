package com.automation.api.interactions.auth;

import com.automation.api.config.ApiEndpoints;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.rest.interactions.Post;

/**
 * Interacción Screenplay: verifica login con email y password vía form params.
 */
public class VerifyLogin implements Interaction {

    private final String email;
    private final String password;

    public VerifyLogin(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to(ApiEndpoints.VERIFY_LOGIN)
                        .with(request -> request
                                .formParam("email", email)
                                .formParam("password", password))
        );
    }

    public static VerifyLogin withCredentials(String email, String password) {
        return new VerifyLogin(email, password);
    }
}
