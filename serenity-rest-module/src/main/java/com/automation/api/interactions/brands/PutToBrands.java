package com.automation.api.interactions.brands;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.rest.interactions.Put;

/**
 * Screenplay Interaction: sends an unsupported PUT to the brands list endpoint.
 * Expected to return responseCode 405.
 */
public class PutToBrands implements Interaction {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Put.to("/api/brandsList")
        );
    }

    public static PutToBrands toApi() {
        return new PutToBrands();
    }
}
