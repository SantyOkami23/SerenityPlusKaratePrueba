package com.automation.api.interactions.search;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.rest.interactions.Post;

/**
 * Screenplay Interaction: searches products by a given search term.
 * Uses form-encoded parameter (not JSON body).
 */
public class SearchProduct implements Interaction {

    private final String searchTerm;

    public SearchProduct(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to("/api/searchProduct")
                        .with(request -> request.formParam("search_product", searchTerm))
        );
    }

    public static SearchProduct withTerm(String searchTerm) {
        return new SearchProduct(searchTerm);
    }
}
