package com.automation.api.interactions.search;

import com.automation.api.config.ApiEndpoints;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.rest.interactions.Post;

/**
 * Interacción Screenplay: busca productos enviando un término de búsqueda.
 * Usa form-encoded (no JSON body) según el contrato de la API.
 */
public class SearchProduct implements Interaction {

    private final String searchTerm;

    public SearchProduct(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to(ApiEndpoints.SEARCH_PRODUCT)
                        .with(request -> request.formParam("search_product", searchTerm))
        );
    }

    public static SearchProduct withTerm(String searchTerm) {
        return new SearchProduct(searchTerm);
    }
}
