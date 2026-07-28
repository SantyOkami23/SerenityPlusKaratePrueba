package com.automation.api.questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.util.List;
import java.util.Map;

/**
 * Pregunta Screenplay: obtiene la lista de productos del body JSON.
 * Devuelve una lista de Maps para validaciones flexibles sin acoplamiento a modelos.
 */
public class ProductsResult implements Question<List<Map<String, Object>>> {

    @Override
    public List<Map<String, Object>> answeredBy(Actor actor) {
        return SerenityRest.lastResponse().path("products");
    }

    public static ProductsResult fromLastResponse() {
        return new ProductsResult();
    }
}
