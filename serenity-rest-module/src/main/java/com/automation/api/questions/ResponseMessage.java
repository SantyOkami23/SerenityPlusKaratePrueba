package com.automation.api.questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

/**
 * Pregunta Screenplay: obtiene el campo "message" del body JSON de la última respuesta.
 */
public class ResponseMessage implements Question<String> {

    @Override
    public String answeredBy(Actor actor) {
        return SerenityRest.lastResponse().path("message");
    }

    public static ResponseMessage fromLastResponse() {
        return new ResponseMessage();
    }
}
