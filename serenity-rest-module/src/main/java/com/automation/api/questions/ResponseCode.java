package com.automation.api.questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

/**
 * Pregunta Screenplay: obtiene el responseCode del body JSON de la última respuesta.
 * AutomationExercise siempre devuelve HTTP 200; el código real está en el body.
 */
public class ResponseCode implements Question<Integer> {

    @Override
    public Integer answeredBy(Actor actor) {
        return SerenityRest.lastResponse().path("responseCode");
    }

    public static ResponseCode fromLastResponse() {
        return new ResponseCode();
    }
}
