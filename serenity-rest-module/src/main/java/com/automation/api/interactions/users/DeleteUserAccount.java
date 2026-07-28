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
        String encodedEmail = java.net.URLEncoder.encode(email, java.nio.charset.StandardCharsets.UTF_8);
        String encodedPassword = java.net.URLEncoder.encode(password, java.nio.charset.StandardCharsets.UTF_8);
        actor.attemptsTo(
                Delete.from(ApiEndpoints.DELETE_ACCOUNT)
                        .with(request -> request
                                .header("Content-Type", "application/x-www-form-urlencoded")
                                .body("email=" + encodedEmail + "&password=" + encodedPassword))
        );
    }

    public static DeleteUserAccount withCredentials(String email, String password) {
        return new DeleteUserAccount(email, password);
    }
}
