package com.automation.api.interactions.common;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.rest.interactions.Post;

/**
 * Interacción Screenplay genérica para peticiones POST sin parámetros.
 * Útil para pruebas negativas de método no soportado (responseCode 405).
 */
public class PostToResource implements Interaction {

    private final String endpoint;

    private PostToResource(String endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to(endpoint)
        );
    }

    public static PostToResource at(String endpoint) {
        return new PostToResource(endpoint);
    }
}
