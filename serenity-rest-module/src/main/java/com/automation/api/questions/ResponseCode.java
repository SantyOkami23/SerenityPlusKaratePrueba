package com.automation.api.questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import io.restassured.path.json.JsonPath;

/**
 * Pregunta Screenplay: obtiene el responseCode del body JSON de la última respuesta.
 * AutomationExercise generalmente devuelve HTTP 200; pero para algunos rechazos usa HTTP status nativo.
 */
public class ResponseCode implements Question<Integer> {

    @Override
    public Integer answeredBy(Actor actor) {
        int httpStatus = SerenityRest.lastResponse().statusCode();
        if (httpStatus != 200) {
            return httpStatus;
        }
        
        try {
            String body = SerenityRest.lastResponse().asString();
            return new JsonPath(body).getInt("responseCode");
        } catch (Exception e) {
            return httpStatus;
        }
    }

    public static ResponseCode fromLastResponse() {
        return new ResponseCode();
    }
}
