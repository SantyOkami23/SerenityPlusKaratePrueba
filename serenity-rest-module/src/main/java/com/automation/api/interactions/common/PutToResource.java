package com.automation.api.interactions.common;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.rest.interactions.Put;

/**
 * Interacción Screenplay genérica para peticiones PUT sin parámetros.
 * Útil para pruebas negativas de método no soportado (responseCode 405).
 */
public class PutToResource implements Interaction {

    private final String endpoint;

    private PutToResource(String endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Put.to(endpoint)
        );
    }

    public static PutToResource at(String endpoint) {
        return new PutToResource(endpoint);
    }
}
