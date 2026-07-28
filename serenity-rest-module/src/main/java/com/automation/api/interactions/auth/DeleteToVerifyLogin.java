package com.automation.api.interactions.auth;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.rest.interactions.Delete;

/**
 * Screenplay Interaction: sends an unsupported DELETE to the verifyLogin endpoint.
 * Expected to return responseCode 405.
 */
public class DeleteToVerifyLogin implements Interaction {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Delete.from("/api/verifyLogin")
        );
    }

    public static DeleteToVerifyLogin fromApi() {
        return new DeleteToVerifyLogin();
    }
}
