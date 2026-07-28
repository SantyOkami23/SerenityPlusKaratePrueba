package com.automation.api.interactions.common;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.rest.interactions.Get;

/**
 * Interacción Screenplay genérica para peticiones GET sin parámetros.
 * Reutilizable para cualquier endpoint que solo requiera un GET simple.
 */
public class GetResource implements Interaction {

    private final String endpoint;

    private GetResource(String endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource(endpoint)
        );
    }

    public static GetResource from(String endpoint) {
        return new GetResource(endpoint);
    }
}
