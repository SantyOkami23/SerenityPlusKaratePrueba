package com.automation.api.interactions.common;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.rest.interactions.Delete;

/**
 * Interacción Screenplay genérica para peticiones DELETE sin parámetros.
 * Útil para pruebas negativas de método no soportado (responseCode 405).
 */
public class DeleteFromResource implements Interaction {

    private final String endpoint;

    private DeleteFromResource(String endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Delete.from(endpoint)
        );
    }

    public static DeleteFromResource at(String endpoint) {
        return new DeleteFromResource(endpoint);
    }
}
