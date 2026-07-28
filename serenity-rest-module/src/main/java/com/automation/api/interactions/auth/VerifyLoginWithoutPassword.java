package com.automation.api.interactions.auth;

import com.automation.api.config.ApiEndpoints;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.rest.interactions.Post;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Interacción Screenplay: intenta login solo con email (sin password).
 * Se espera responseCode 400 con mensaje de parámetro faltante.
 */
public class VerifyLoginWithoutPassword implements Interaction {

    private final String email;

    public VerifyLoginWithoutPassword(String email) {
        this.email = email;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        String encodedEmail = URLEncoder.encode(email, StandardCharsets.UTF_8);
        actor.attemptsTo(
                Post.to(ApiEndpoints.VERIFY_LOGIN)
                        .with(request -> request
                                .header("Content-Type", "application/x-www-form-urlencoded")
                                .body("email=" + encodedEmail))
        );
    }

    public static VerifyLoginWithoutPassword withEmail(String email) {
        return new VerifyLoginWithoutPassword(email);
    }
}
