package com.automation.api.interactions.search;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.rest.interactions.Post;

/**
 * Screenplay Interaction: sends a search request without the required parameter.
 * Expected to return responseCode 400.
 */
public class SearchProductWithoutParam implements Interaction {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to("/api/searchProduct")
        );
    }

    public static SearchProductWithoutParam toApi() {
        return new SearchProductWithoutParam();
    }
}
