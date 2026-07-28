package com.automation.api.questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.util.List;
import java.util.Map;

/**
 * Pregunta Screenplay: obtiene la lista de marcas del body JSON.
 */
public class BrandsResult implements Question<List<Map<String, Object>>> {

    @Override
    public List<Map<String, Object>> answeredBy(Actor actor) {
        return SerenityRest.lastResponse().path("brands");
    }

    public static BrandsResult fromLastResponse() {
        return new BrandsResult();
    }
}
